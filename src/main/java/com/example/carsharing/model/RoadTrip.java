package com.example.carsharing.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class RoadTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roadId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id")
    private Car car;

    private Integer distance;
    private String time;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer amount;
    private Integer fuel;
    private Integer avgSpeed;
    @Column (nullable = false)
    private boolean hasTrafficViolations;
}
