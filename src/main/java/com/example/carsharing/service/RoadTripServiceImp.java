package com.example.carsharing.service;

import com.example.carsharing.model.Car;
import com.example.carsharing.model.Customer;
import com.example.carsharing.model.RoadTrip;
import com.example.carsharing.model.RoadTripDTO;
import com.example.carsharing.repository.CarRepository;
import com.example.carsharing.repository.CustomerRepository;
import com.example.carsharing.repository.RoadTripRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoadTripServiceImp implements RoadTripService{
    private final RoadTripRepository repo;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    @Override
    public RoadTrip createRoad(RoadTripDTO roadtrip) {
        Customer customer = customerRepository.findById(roadtrip.getCustomerId()).get();
        Car car = carRepository.findById(roadtrip.getCarId()).get();
        RoadTrip roadTrip = new RoadTrip();
        roadTrip.setCustomer(customer);
        roadTrip.setCar(car);
        roadTrip.setDistance(roadtrip.getDistance());
        return roadTrip;
    }

    @Override
    public List<RoadTrip> readAllTrip() {
        return repo.findAll();
    }

    @Override
    public RoadTrip updateRoad(RoadTripDTO roadTrip) {
        return null;
    }

    @Override
    public void deleteRoad(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}
