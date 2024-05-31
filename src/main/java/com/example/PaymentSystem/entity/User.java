package com.example.PaymentSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
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
    String password;

    @Column(nullable = false)
    String title;

    @Column(nullable = true)
    Integer INN;

    @Column(nullable = true)
    Integer bankAccount;

    @Column(nullable = false, unique = true)
    UUID appId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "role_id")
    UserRole role;

    @Column(name = "reset_token")
    String resetToken;

    @Column(name = "reset_token_expire_time")
    LocalDateTime resetTokenExpireTime;

    public User(Integer id, String email, String password, String title, Integer INN, Integer bankAccount, UUID appId, UserRole role, String resetToken, LocalDateTime resetTokenExpireTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.title = title;
        this.INN = INN;
        this.bankAccount = bankAccount;
        this.appId = UUID.randomUUID();
        this.role = role;
        this.resetToken = resetToken;
        this.resetTokenExpireTime = resetTokenExpireTime;
    }
}
