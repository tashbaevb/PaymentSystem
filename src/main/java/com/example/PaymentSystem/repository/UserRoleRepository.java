package com.example.PaymentSystem.repository;

import com.example.PaymentSystem.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByRole(String role);
}
