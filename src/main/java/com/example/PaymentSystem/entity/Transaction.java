// Transaction.java
package com.example.PaymentSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String buyerName;

    Integer buyerBankAccount;

    LocalDateTime transactionTime;

    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
