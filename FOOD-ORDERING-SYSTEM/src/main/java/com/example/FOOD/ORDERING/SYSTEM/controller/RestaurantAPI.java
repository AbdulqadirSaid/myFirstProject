package com.example.FOOD.ORDERING.SYSTEM.controller;

import com.example.FOOD.ORDERING.SYSTEM.model.Restaurant;
import com.example.FOOD.ORDERING.SYSTEM.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantAPI {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
        try {
            Restaurant savedRestaurant = restaurantRepo.save(restaurant);
            return new ResponseEntity<>(savedRestaurant, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getRestaurants() {
        try {
            List<Restaurant> restaurantList = restaurantRepo.findAll();
            if (restaurantList.isEmpty()) {
                return new ResponseEntity<>("No restaurants found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(restaurantList, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Long id) {
        try {
            Optional<Restaurant> restaurant = restaurantRepo.findById(id);
            if (restaurant.isPresent()) {
                return new ResponseEntity<>(restaurant.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurantDetails) {
        try {
            Optional<Restaurant> restaurant = restaurantRepo.findById(id);
            if (restaurant.isPresent()) {
                Restaurant existingRestaurant = restaurant.get();
                existingRestaurant.setRestName(restaurantDetails.getRestName());
                existingRestaurant.setRestAddress(restaurantDetails.getRestAddress());
                existingRestaurant.setRestLocation(restaurantDetails.getRestLocation());

                Restaurant updatedRestaurant = restaurantRepo.save(existingRestaurant);
                return new ResponseEntity<>(updatedRestaurant, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        try {
            Optional<Restaurant> restaurant = restaurantRepo.findById(id);
            if (restaurant.isPresent()) {
                restaurantRepo.delete(restaurant.get());
                return new ResponseEntity<>("Restaurant deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

