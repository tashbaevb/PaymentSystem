package com.example.PaymentSystem.service;

import com.example.PaymentSystem.dto.TransactionResponseWrapper;
import com.example.PaymentSystem.dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    ResponseEntity<List<UserResponseDto>> getAllUsers();

//    ResponseEntity<List<TransactionResponseDto>> getAllTransactions();

    ResponseEntity<TransactionResponseWrapper> getAllTransactions(String sort);
}
