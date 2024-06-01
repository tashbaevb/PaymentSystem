package com.example.PaymentSystem.service;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TransactionService {

    ResponseEntity<String> createTransaction(TransactionRequest request);

    List<TransactionResponseDto> getUserTransactions(Authentication authentication);

    List<TransactionResponseDto> getTransactionsForLastMonth(Authentication authentication);

    List<TransactionResponseDto> getTransactionsForLastWeek(Authentication authentication);

    List<TransactionResponseDto> getTransactionsForLastDay(Authentication authentication);
}
