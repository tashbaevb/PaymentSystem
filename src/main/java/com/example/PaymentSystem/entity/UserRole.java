package com.example.PaymentSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false, unique = true)
    String role;
}
