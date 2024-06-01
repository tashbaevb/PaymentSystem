package com.example.PaymentSystem.service.impl;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.entity.Transaction;
import com.example.PaymentSystem.entity.User;
import com.example.PaymentSystem.mapper.TransactionMapper;
import com.example.PaymentSystem.repository.TransactionRepository;
import com.example.PaymentSystem.repository.UserRepository;
import com.example.PaymentSystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<String> createTransaction(TransactionRequest request) {
        User user = userRepository.findByAppId(request.getAppId());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found for provided appId");
        }

        BigDecimal price = request.getPrice();

        BigDecimal percent = new BigDecimal("0.01");
        BigDecimal discount = price.multiply(percent);
        BigDecimal discountedPrice = price.subtract(discount);

        Transaction transaction = new Transaction();
        transaction.setBuyerName(request.getBuyerName());
        transaction.setBuyerBankAccount(request.getBuyerBankAccount());
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setPrice(discountedPrice.setScale(2, RoundingMode.HALF_UP));
        transaction.setUser(user);

        user.getTransactions().add(transaction);

        userRepository.save(user);

        return ResponseEntity.ok("Transaction created successfully");
    }

    @Override
    public List<TransactionResponseDto> getUserTransactions(Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return null;
        }
        List<Transaction> userTransactions = user.getTransactions();
        return transactionMapper.convertToDtoList(userTransactions);
    }

    @Override
    public List<TransactionResponseDto> getTransactionsForLastMonth(Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        List<Transaction> transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, oneMonthAgo, now);

        return transactionMapper.convertToDtoList(transactions);
    }

    @Override
    public List<TransactionResponseDto> getTransactionsForLastWeek(Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusWeeks(1);
        List<Transaction> transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, oneWeekAgo, now);

        return transactionMapper.convertToDtoList(transactions);
    }

    @Override
    public List<TransactionResponseDto> getTransactionsForLastDay(Authentication authentication) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return null;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayAgo = now.minusDays(1);
        List<Transaction> transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, oneDayAgo, now);

        return transactionMapper.convertToDtoList(transactions);
    }
}
