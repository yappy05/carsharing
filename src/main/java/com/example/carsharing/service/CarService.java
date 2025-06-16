package com.example.carsharing.service;

import com.example.carsharing.model.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CarService {
    Car createCar (Car car);
    List<Car> readAllCar();
    Car updateCar (Car car);
    void deleteCar(Long id);
    void deleteAllCars();
}
