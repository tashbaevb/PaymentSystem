package com.example.PaymentSystem.service;

import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.dto.TransactionResponseWrapper;
import com.example.PaymentSystem.dto.UserDto;
import com.example.PaymentSystem.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    ResponseEntity<List<UserDto>> getAllUsers();

//    ResponseEntity<List<TransactionResponseDto>> getAllTransactions();

    ResponseEntity<TransactionResponseWrapper> getAllTransactions(String sort);
}
