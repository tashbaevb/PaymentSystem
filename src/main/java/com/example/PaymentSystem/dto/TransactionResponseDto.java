package com.example.PaymentSystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponseDto {

    private Integer id;
    private String buyerName;
    private Integer buyerBankAccount;
    private LocalDateTime transactionTime;
    private BigDecimal price;

}
