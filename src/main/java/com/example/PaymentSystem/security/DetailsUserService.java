package com.example.PaymentSystem.security;

import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.exception.NotFoundException;
import com.example.PaymentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailsUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws NotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new NotFoundException("Email not found! ", HttpStatus.NOT_FOUND);

        return new DetailsUser(user.get());
    }
}
