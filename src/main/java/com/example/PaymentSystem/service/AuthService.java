// AuthService.java
package com.example.PaymentSystem.service;

import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.entity.UserRole;

public interface AuthService {
    UserRole createRole(UserRole userRole);
    void register(User user);
    boolean isPresentEmail(String email);
    String resetPassword(String email);
    String saveNewPassword(String resetToken, String newPassword);
}
