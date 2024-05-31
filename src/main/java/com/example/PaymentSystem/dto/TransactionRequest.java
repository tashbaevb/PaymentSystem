package com.example.PaymentSystem.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {

    String buyerName;

    Integer buyerBankAccount;

    UUID appId;

    BigDecimal price;
}

