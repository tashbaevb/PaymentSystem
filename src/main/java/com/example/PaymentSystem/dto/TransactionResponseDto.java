package com.example.PaymentSystem.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponseDto {

    Integer id;

    String buyerName;

    Integer buyerBankAccount;

    LocalDateTime transactionTime;

    BigDecimal price;
}
