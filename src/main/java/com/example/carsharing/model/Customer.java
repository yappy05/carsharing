package com.example.carsharing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;
    private String lastname;
    private String login;
    private String password;
    private String address;

    @Column(name = "is_subscribe", nullable = false, columnDefinition = "boolean default false")
    private boolean isSubscribe;

    @Column(name = "has_driving_license", nullable = false, columnDefinition = "boolean default false")
    private boolean hasDrivingLicense;

    private Integer drivingExperience;
}

