package com.example.PaymentSystem.dto;

import java.util.List;

public class TransactionResponseWrapper {
    private long totalCount;
    private List<TransactionResponseDto> transactions;

    public TransactionResponseWrapper(long totalCount, List<TransactionResponseDto> transactions) {
        this.totalCount = totalCount;
        this.transactions = transactions;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<TransactionResponseDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponseDto> transactions) {
        this.transactions = transactions;
    }
}
