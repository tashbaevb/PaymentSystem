package com.example.PaymentSystem.service;

public interface EmailService {

    void sendMessage(String to, String subject, String text);
}
