package com.example.PaymentSystem.mapper;

import com.example.PaymentSystem.dto.TransactionResponseDto;
import com.example.PaymentSystem.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final ModelMapper modelMapper;

    public TransactionResponseDto convertToDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionResponseDto.class);
    }

    public List<TransactionResponseDto> convertToDtoList(List<Transaction> transactions) {
        return transactions.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
