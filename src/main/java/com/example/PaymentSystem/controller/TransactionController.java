package com.example.PaymentSystem.controller;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.entity.Transaction;
import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
        User user = userRepository.findByAppId(request.getAppId());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found for provided appId");
        }

        Transaction transaction = new Transaction();
        transaction.setBuyerName(request.getBuyerName());
        transaction.setBuyerBankAccount(request.getBuyerBankAccount());
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setPrice(request.getPrice());
        transaction.setUser(user);

        user.getTransactions().add(transaction);

        userRepository.save(user);

        return ResponseEntity.ok("Transaction created successfully");
    }

    @GetMapping("/user-transactions")
    public ResponseEntity<List<TransactionResponseDto>> getUserTransactions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Transaction> userTransactions = user.getTransactions();

        List<TransactionResponseDto> transactionDtos = userTransactions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transactionDtos);
    }

    private TransactionResponseDto convertToDto(Transaction transaction) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(transaction.getId());
        dto.setBuyerName(transaction.getBuyerName());
        dto.setBuyerBankAccount(transaction.getBuyerBankAccount());
        dto.setTransactionTime(transaction.getTransactionTime());
        dto.setPrice(transaction.getPrice());
        return dto;
    }
}
