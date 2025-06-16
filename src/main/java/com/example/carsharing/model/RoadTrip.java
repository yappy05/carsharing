package com.example.carsharing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.smartcardio.Card;
import java.util.Date;

@Entity
@Table(name = "road_trip")
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
    private Card card;

    private Integer distance;
    private Date time;
    private Integer amount;
    private Integer fuel;
    private Integer avgSpeed;
    private boolean hasTrafficViolations;
}