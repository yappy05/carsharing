package com.example.carsharing.service;

import com.example.carsharing.model.RoadTrip;
import com.example.carsharing.model.RoadTripDTO;

import java.util.List;

public interface RoadTripService {
    RoadTrip createRoad(RoadTripDTO roadTrip);
    List<RoadTrip> readAllTrip();
    RoadTrip updateRoad(RoadTripDTO roadTrip);
    void deleteRoad(Long id);
    void deleteAll();
}
