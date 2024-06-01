package com.example.PaymentSystem.controller;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseWrapper;
import com.example.PaymentSystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
        return transactionService.createTransaction(request);
    }

    @GetMapping("/getCompanies")
    public ResponseEntity<TransactionResponseWrapper> getUserTransactions(
            Authentication authentication,
            @RequestParam(required = false) String sort) {

        TransactionResponseWrapper transactionResponseWrapper = transactionService.getUserTransactions(authentication, sort);
        if (transactionResponseWrapper == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactionResponseWrapper);
    }
}
