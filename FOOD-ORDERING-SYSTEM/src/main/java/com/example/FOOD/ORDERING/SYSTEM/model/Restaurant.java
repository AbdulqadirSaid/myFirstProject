package com.example.FOOD.ORDERING.SYSTEM.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restId;

    private String restName;
    private String restAddress;
    private String restLocation;

    // Constructors
    public Restaurant() {}

    public Restaurant(String restName, String restAddress, String restLocation) {
        this.restName = restName;
        this.restAddress = restAddress;
        this.restLocation = restLocation;
    }

    // Getters and Setters
    public Long getRestId() {
        return restId;
    }

    public void setRestId(Long restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    public String getRestLocation() {
        return restLocation;
    }

    public void setRestLocation(String restLocation) {
        this.restLocation = restLocation;
    }
}