package com.example.PaymentSystem.service.impl;

import com.example.PaymentSystem.entity.Transaction;
import com.example.PaymentSystem.repository.TransactionRepository;
import com.example.PaymentSystem.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final TransactionRepository transactionRepository;

    @Override
    public byte[] generateEarningsChart(String userEmail, String sort) throws IOException {
        List<Transaction> transactions;
        LocalDateTime now = LocalDateTime.now();

        if ("month".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByTransactionTimeBetween(now.minusMonths(1), now);
        } else if ("week".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByTransactionTimeBetween(now.minusWeeks(1), now);
        } else if ("day".equalsIgnoreCase(sort)) {
            transactions = transactionRepository.findAllByTransactionTimeBetween(now.minusDays(1), now);
        } else {
            transactions = transactionRepository.findAll();
        }

        // Группируем транзакции по дате
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Transaction transaction : transactions) {
            LocalDate date = transaction.getTransactionTime().toLocalDate();
            BigDecimal price = transaction.getPrice();
            dataset.addValue(price, "Earnings", date);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Earnings",
                "Date",
                "Amount",
                dataset
        );

        // Генерируем изображение графика
        BufferedImage chartImage = barChart.createBufferedImage(800, 600);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtils.writeBufferedImageAsPNG(baos, chartImage);

        return baos.toByteArray();
    }
}

