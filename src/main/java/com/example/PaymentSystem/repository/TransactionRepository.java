package com.example.PaymentSystem.repository;

import com.example.PaymentSystem.entity.Transaction;
import com.example.PaymentSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserAndTransactionTimeBetween(User user, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
