package com.example.PaymentSystem.controller;

import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.dto.UserDto;
import com.example.PaymentSystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        return adminService.getAllTransactions();
    }
}
