package com.example.FOOD.ORDERING.SYSTEM.controller;

import com.example.FOOD.ORDERING.SYSTEM.model.MenuItems;
import com.example.FOOD.ORDERING.SYSTEM.repository.MenuItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemsController {
    private final MenuItemsRepository menuItemsRepository;

    @Autowired
    public MenuItemsController(MenuItemsRepository menuItemsRepository) {
        this.menuItemsRepository = menuItemsRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<List<MenuItems>> getAllMenuItems() {
        List<MenuItems> menuItems = menuItemsRepository.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<MenuItems> getMenuItemsById(@PathVariable Long id) {
        MenuItems menuItems = menuItemsRepository.findById(id).orElse(null);
        if (menuItems == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<MenuItems> createMenuItems(@RequestBody MenuItems menuItems) {
        MenuItems newMenuItems = menuItemsRepository.save(menuItems);
        return new ResponseEntity<>(newMenuItems, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<MenuItems> updateMenuItems(@PathVariable Long id, @RequestBody MenuItems menuItems) {
        MenuItems existingMenuItems = menuItemsRepository.findById(id).orElse(null);
        if (existingMenuItems == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingMenuItems.setFoodName(menuItems.getFoodName());
        existingMenuItems.setQuantity(menuItems.getQuantity());
        existingMenuItems.setPrice(menuItems.getPrice());
        existingMenuItems.setDescription(menuItems.getDescription());
        existingMenuItems.setStatus(menuItems.getStatus());
        MenuItems updatedMenuItems = menuItemsRepository.save(existingMenuItems);
        return new ResponseEntity<>(updatedMenuItems, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItems(@PathVariable Long id) {
        menuItemsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
