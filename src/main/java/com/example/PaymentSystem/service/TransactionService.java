package com.example.PaymentSystem.service;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.dto.TransactionResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    ResponseEntity<String> createTransaction(TransactionRequest request);

    TransactionResponseWrapper getUserTransactions(Authentication authentication, String sort);

    List<TransactionResponseDto> getTransactionsForPeriod(Authentication authentication, LocalDateTime startDate, LocalDateTime endDate);

    List<TransactionResponseDto> getTransactionsForLastMonth(Authentication authentication);

    List<TransactionResponseDto> getTransactionsForLastWeek(Authentication authentication);

    List<TransactionResponseDto> getTransactionsForLastDay(Authentication authentication);
}
