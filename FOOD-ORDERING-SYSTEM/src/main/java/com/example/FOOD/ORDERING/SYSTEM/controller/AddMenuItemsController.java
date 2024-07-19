package com.example.FOOD.ORDERING.SYSTEM.controller;

import com.example.FOOD.ORDERING.SYSTEM.model.AddMenuItems;
import com.example.FOOD.ORDERING.SYSTEM.repository.AddMenuItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/add-menu-items")
public class AddMenuItemsController {;

    @Autowired
    private AddMenuItemsRepo addMenuItemsRepo;

    @PostMapping
    public ResponseEntity<AddMenuItems> addMenuItem(@RequestBody AddMenuItems addMenuItems) {
        AddMenuItems savedMenuItem = addMenuItemsRepo.save(addMenuItems);
        return new ResponseEntity<>(savedMenuItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddMenuItems>> getAllMenuItems() {
        List<AddMenuItems> menuItems = addMenuItemsRepo.findAll();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddMenuItems> getMenuItemById(@PathVariable Long id) {
        AddMenuItems menuItem = addMenuItemsRepo.findById(id).orElse(null);
        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddMenuItems> updateMenuItem(@PathVariable Long id, @RequestBody AddMenuItems updatedMenuItem) {
        AddMenuItems existingMenuItem = addMenuItemsRepo.findById(id).orElse(null);
        if (existingMenuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingMenuItem.setFoodName(updatedMenuItem.getFoodName());
        existingMenuItem.setDescription(updatedMenuItem.getDescription());
        existingMenuItem.setPrice(updatedMenuItem.getPrice());
        AddMenuItems savedMenuItem = addMenuItemsRepo.save(existingMenuItem);
        return new ResponseEntity<>(savedMenuItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        addMenuItemsRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}