package com.example.PaymentSystem.service;


import java.io.IOException;

public interface ChartService {
    byte[] generateEarningsChart(String userEmail, String sort) throws IOException;
}

