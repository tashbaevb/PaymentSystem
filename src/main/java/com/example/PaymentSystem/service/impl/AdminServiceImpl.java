package com.example.PaymentSystem.service.impl;

import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.dto.TransactionResponseWrapper;
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

import java.time.LocalDateTime;
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
    public ResponseEntity<TransactionResponseWrapper> getAllTransactions(String sort) {
        List<Transaction> transactions;
        LocalDateTime now = LocalDateTime.now();

        if ("month".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByTransactionTimeBetween(now.minusMonths(1), now);
        } else if ("week".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByTransactionTimeBetween(now.minusWeeks(1), now);
        } else if ("day".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByTransactionTimeBetween(now.minusDays(1), now);
        } else {
            transactions = transactionRepository.findAll();
        }

        List<TransactionResponseDto> transactionDtos = transactionMapper.convertToDtoList(transactions);
        TransactionResponseWrapper responseWrapper = new TransactionResponseWrapper(transactionDtos.size(), transactionDtos);

        return ResponseEntity.ok(responseWrapper);
    }
}
