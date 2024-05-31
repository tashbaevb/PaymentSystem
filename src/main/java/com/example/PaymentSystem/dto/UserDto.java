// UserDto.java
package com.example.PaymentSystem.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    String email;

    String password;

    String title;

    Integer INN;

    Integer bankAccount;

    UUID appId;
}
