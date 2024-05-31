package com.example.PaymentSystem.controller;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/user-transactions")
    public ResponseEntity<List<TransactionResponseDto>> getUserTransactions(Authentication authentication) {
        List<TransactionResponseDto> transactionDtos = transactionService.getUserTransactions(authentication);
        if (transactionDtos == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactionDtos);
    }
}
