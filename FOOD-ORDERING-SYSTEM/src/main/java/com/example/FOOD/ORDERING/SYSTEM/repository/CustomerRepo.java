package com.example.FOOD.ORDERING.SYSTEM.repository;

import com.example.FOOD.ORDERING.SYSTEM.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
