package com.example.FOOD.ORDERING.SYSTEM.repository;



import com.example.FOOD.ORDERING.SYSTEM.model.ViewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ViewOrderRepository extends JpaRepository<ViewOrder, Long> {
//    @Override
//    Optional<ViewOrder> findById(Long id);
}

