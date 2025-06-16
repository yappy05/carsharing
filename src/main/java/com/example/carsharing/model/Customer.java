package com.example.carsharing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    private String name;
    private String lastName;
    private String login;
    private String password;
    private String adress;
    private Integer drivingExperience;
    @Column(nullable = false)
    private boolean isSubscribe;
    @Column(nullable = false)
    private boolean hasDrivingLicense;
}