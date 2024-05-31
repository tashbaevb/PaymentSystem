package com.example.PaymentSystem.dto;

import java.math.BigDecimal;
import java.util.UUID;


public class TransactionRequest {

    private String buyerName;
    private Integer buyerBankAccount;
    private UUID appId;
    private BigDecimal price;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Integer getBuyerBankAccount() {
        return buyerBankAccount;
    }

    public void setBuyerBankAccount(Integer buyerBankAccount) {
        this.buyerBankAccount = buyerBankAccount;
    }

    public UUID getAppId() {
        return appId;
    }

    public void setAppId(UUID appId) {
        this.appId = appId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

