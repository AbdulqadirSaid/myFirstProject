package com.example.FOOD.ORDERING.SYSTEM.repository;

import com.example.FOOD.ORDERING.SYSTEM.model.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Long> {

}