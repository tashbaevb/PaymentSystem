package com.example.PaymentSystem.service.impl;

import com.example.PaymentSystem.dto.TransactionRequest;
import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.dto.TransactionResponseWrapper;
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
    public TransactionResponseWrapper getUserTransactions(Authentication authentication, String sort) {
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElse(null);
        if (user == null) {
            return null;
        }

        List<Transaction> transactions;
        LocalDateTime now = LocalDateTime.now();
        if ("month".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, now.minusMonths(1), now);
        } else if ("week".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, now.minusWeeks(1), now);
        } else if ("day".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, now.minusDays(1), now);
        } else {
            transactions = user.getTransactions();
        }

        List<TransactionResponseDto> transactionDtos = transactionMapper.convertToDtoList(transactions);
        return new TransactionResponseWrapper(transactionDtos.size(), transactionDtos);
    }


    //    @Override
//    public List<TransactionResponseDto> getTransactionsForPeriod(Authentication authentication, LocalDateTime startDate, LocalDateTime endDate) {
//        String userEmail = authentication.getName();
//        User user = userRepository.findByEmail(userEmail).orElse(null);
//        if (user == null) {
//            return null;
//        }
//
//        List<Transaction> transactions = transactionRepository.findAllByUserAndTransactionTimeBetween(user, startDate, endDate);
//
//        return transactionMapper.convertToDtoList(transactions);
//    }

//    @Override
//    public List<TransactionResponseDto> getTransactionsForLastMonth(Authentication authentication) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime oneMonthAgo = now.minusMonths(1);
//        return getTransactionsForPeriod(authentication, oneMonthAgo, now);
//    }

//    @Override
//    public List<TransactionResponseDto> getTransactionsForLastWeek(Authentication authentication) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime oneWeekAgo = now.minusWeeks(1);
//        return getTransactionsForPeriod(authentication, oneWeekAgo, now);
//    }
//
//    @Override
//    public List<TransactionResponseDto> getTransactionsForLastDay(Authentication authentication) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime oneDayAgo = now.minusDays(1);
//        return getTransactionsForPeriod(authentication, oneDayAgo, now);
//    }
}
