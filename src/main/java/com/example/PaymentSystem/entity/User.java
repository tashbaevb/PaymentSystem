package com.example.PaymentSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
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
    String password, title;

    @Column(nullable = true)
    Integer INN, bank_account;

    @Column(nullable = false, unique = true)
    UUID appId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    UserRole role;

    @Column(name = "reset_token")
    String resetToken;

    @Column(name = "reset_token_expire_time")
    LocalDateTime resetTokenExpireTime;

    public User(Integer id, String email, String password, String title, Integer INN, Integer bank_account, UUID appId, UserRole role, String resetToken, LocalDateTime resetTokenExpireTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.title = title;
        this.INN = INN;
        this.bank_account = bank_account;
        this.appId = UUID.randomUUID();
        this.role = role;
        this.resetToken = resetToken;
        this.resetTokenExpireTime = resetTokenExpireTime;
    }
}
