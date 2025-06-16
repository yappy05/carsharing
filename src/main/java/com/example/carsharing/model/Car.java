package com.example.carsharing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    private String brand;
    private String model;
    private String speed;
    private String mileage;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "fuel_consumption")
    private Integer fuelConsumption;

    @Column(name = "price_per_minute")
    private Integer pricePerMinute;

    private String maintenance;
    private String location;
    private String carClass;

    @Column(name = "is_booking")
    private boolean isBooking;
}