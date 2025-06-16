package com.example.carsharing.controller;

import com.example.carsharing.model.RoadTrip;
import com.example.carsharing.model.RoadTripDTO;
import com.example.carsharing.service.RoadTripService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roadtrip")
public class RoadTripController {
    private final RoadTripService roadTripService;
    @PostMapping("")
    RoadTrip createRoad(@RequestBody RoadTripDTO roadTrip){
        return roadTripService.createRoad(roadTrip);
    }
    @GetMapping("")
    List<RoadTrip> readAllRoad(){
        return roadTripService.readAllTrip();
    }
    @PutMapping("")
    RoadTrip updateRoad(@RequestBody RoadTripDTO roadTrip){
        return roadTripService.updateRoad(roadTrip);
    }
    @DeleteMapping("/delete/{id}")
    HttpStatus deleteRoad(@PathVariable Long id){
        roadTripService.deleteRoad(id);
        return HttpStatus.OK;
    }
}
