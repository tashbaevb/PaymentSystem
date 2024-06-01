package com.example.PaymentSystem.controller;

import com.example.PaymentSystem.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/transactions/earningsChart")
    public ResponseEntity<byte[]> getEarningsChart(Authentication authentication, @RequestParam(required = false) String sort) throws IOException {
        String userEmail = authentication.getName();
        byte[] chart = chartService.generateEarningsChart(userEmail, sort);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(chart.length);

        return ResponseEntity.ok().headers(headers).body(chart);
    }
}

