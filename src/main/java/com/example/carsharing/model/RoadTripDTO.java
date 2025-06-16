package com.example.carsharing.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoadTripDTO {
    private Long customerId;
    private Long carId;
    private int distance;
    private String time;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double amount;
    private double fuel;
    private int avgSpeed;
    private boolean hasTrafficViolations;
}
