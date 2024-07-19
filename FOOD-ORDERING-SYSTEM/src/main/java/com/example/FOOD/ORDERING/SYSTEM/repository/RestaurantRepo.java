package com.example.FOOD.ORDERING.SYSTEM.repository;

import com.example.FOOD.ORDERING.SYSTEM.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
}
