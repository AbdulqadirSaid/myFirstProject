package com.example.FOOD.ORDERING.SYSTEM.controller;

import com.example.FOOD.ORDERING.SYSTEM.model.ViewOrder;
import com.example.FOOD.ORDERING.SYSTEM.repository.ViewOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/view-orders")
@CrossOrigin(origins = "http://localhost:3001") // Allow CORS for React frontend
public class ViewOrderController {

    @Autowired
    private ViewOrderRepository viewOrderRepository;

    // GET order by ID
    @GetMapping("/{id}")
    public ViewOrder getViewOrderById(@PathVariable Long id) {
        return viewOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // GET all orders
    @GetMapping
    public List<ViewOrder> getAllViewOrders() {
        return viewOrderRepository.findAll();
    }

    // POST create a new order
    @PostMapping
    public ViewOrder createViewOrder(@RequestBody ViewOrder viewOrder) {
        return viewOrderRepository.save(viewOrder);
    }

    // PUT update an existing order
    @PutMapping("/{id}")
    public ViewOrder updateViewOrder(@PathVariable Long id, @RequestBody ViewOrder viewOrder) {
        ViewOrder existingViewOrder = viewOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        existingViewOrder.setFoodName(viewOrder.getFoodName());
        existingViewOrder.setQuantity(viewOrder.getQuantity());
        existingViewOrder.setPrice(viewOrder.getPrice());
        existingViewOrder.setDeliveryDate(viewOrder.getDeliveryDate());
        existingViewOrder.setDeliveryTime(viewOrder.getDeliveryTime());
        return viewOrderRepository.save(existingViewOrder);
    }

    // DELETE an order
    @DeleteMapping("/{id}")
    public void deleteViewOrder(@PathVariable Long id) {
        viewOrderRepository.deleteById(id);
    }
}
