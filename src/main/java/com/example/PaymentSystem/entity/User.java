package com.example.PaymentSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    UserRole role;

    @Column(name = "reset_token")
    String resetToken;

    @Column(name = "reset_token_expire_time")
    LocalDateTime resetTokenExpireTime;
}
