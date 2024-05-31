package com.example.PaymentSystem.service;

import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.entity.UserRole;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    UserRole createRole(UserRole userRole);

    void register(User user);

    boolean isPresentEmail(String email);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    String resetPassword(String email);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    String saveNewPassword(String resetToken, String newPassword);
}
