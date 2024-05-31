package com.example.PaymentSystem.service.impl;

import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.dto.UserDto;
import com.example.PaymentSystem.entity.Transaction;
import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.mapper.TransactionMapper;
import com.example.PaymentSystem.mapper.UserMapper;
import com.example.PaymentSystem.repository.TransactionRepository;
import com.example.PaymentSystem.repository.UserRepository;
import com.example.PaymentSystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final UserMapper userMapper;
    private final TransactionMapper transactionMapper;

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(userMapper.toDtoList(users));
    }

    @Override
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<Transaction> transactionResponseDtos = transactionRepository.findAll();

        return ResponseEntity.ok(transactionMapper.convertToDtoList(transactionResponseDtos));
    }
}
