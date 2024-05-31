package com.example.PaymentSystem.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    String email, password, title;

    Integer INN, bank_account;

    UUID appId;
}
