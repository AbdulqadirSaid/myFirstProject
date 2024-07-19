package com.example.FOOD.ORDERING.SYSTEM.controller;

import com.example.FOOD.ORDERING.SYSTEM.model.Customer;
import com.example.FOOD.ORDERING.SYSTEM.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        try {
            Customer customer1 = customerRepo.save(customer);
            return new ResponseEntity<>(customer1, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getCustomer() {
        try {
            List<Customer> customerList = customerRepo.findAll();
            if (customerList.isEmpty()) {
                return new ResponseEntity<>("No customer found", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(customerList, HttpStatus.OK);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepo.findById(id);
            if (customer.isPresent()) {
                return new ResponseEntity<>(customer.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        try {
            Optional<Customer> customer = customerRepo.findById(id);
            if (customer.isPresent()) {
                Customer existingCustomer = customer.get();
                existingCustomer.setCustName(customerDetails.getCustName());
                existingCustomer.setCustEmail(customerDetails.getCustEmail());
                // Set other fields as necessary

                Customer updatedCustomer = customerRepo.save(existingCustomer);
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerRepo.findById(id);
            if (customer.isPresent()) {
                customerRepo.delete(customer.get());
                return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
