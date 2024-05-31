// UserRepository.java
package com.example.PaymentSystem.repository;

import com.example.PaymentSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findByResetToken(String resetToken);
//    Optional<User> findByAppId1(UUID appId);
    User findByAppId(UUID appId);
}

