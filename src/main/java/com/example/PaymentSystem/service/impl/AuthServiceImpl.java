package com.example.PaymentSystem.service.impl;

import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.entity.UserRole;
import com.example.PaymentSystem.repository.UserRepository;
import com.example.PaymentSystem.repository.UserRoleRepository;
import com.example.PaymentSystem.service.AuthService;
import com.example.PaymentSystem.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Value("${resetUrl}")
    private String resetUrl;

    @Override
    public UserRole createRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRole userRole = userRoleRepository.findByRole("ROLE_USER");

        user.setRole(userRole);
        user.setAppId(UUID.randomUUID());

        userRepository.save(user);
    }


    @Override
    public boolean isPresentEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String resetPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return "Email not found";
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
        userRepository.save(user.get());

        String resetUrlFin = resetUrl + resetToken;
        String emailText = "Hello! " +
                "\n\nnPlease follow this link to reset your password: " + resetUrlFin;

        emailService.sendMessage(email, "Reset password", emailText);
        return "A password reset link has been sent to your e-mail address " + email;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return "Der Link zum Zurücksetzen des Passworts ist veraltet";

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);
        return "Das Passwort wurde erfolgreich verändert. ";
    }
}
