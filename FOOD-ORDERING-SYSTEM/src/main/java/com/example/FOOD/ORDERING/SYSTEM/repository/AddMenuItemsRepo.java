package com.example.FOOD.ORDERING.SYSTEM.repository;

import com.example.FOOD.ORDERING.SYSTEM.model.AddMenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddMenuItemsRepo extends JpaRepository<AddMenuItems, Long> {
}
