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
public class RoadTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roadTripId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Car car;

    private Integer distance;
    private Date time;
    private Integer amount;
    private Integer fuel;
    private Integer avgSpeed;
    private boolean hasTrafficViolations;
}