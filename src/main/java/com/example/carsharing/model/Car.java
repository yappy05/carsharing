package com.example.carsharing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String brend;
    private String model;
    private Integer speed;        // Изменено на Integer
    private Integer mileage;      // Исправлено написание
    private String licensePlate;  // Исправлено написание
    private Integer fuelConsumption;
    private Integer pricePerMinute;
    private String maintenance;
    private String location;
    private String levelcomfort;

    @Column(nullable = false)
    private boolean isBooking;
}
