package com.example.PaymentSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseWrapper {

    private long totalCount;

    private List<TransactionResponseDto> transactions;
}
