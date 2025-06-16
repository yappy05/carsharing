package com.example.carsharing.service;

import com.example.carsharing.model.Car;
import com.example.carsharing.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImp implements CarService{

    private final CarRepository repo;

    @Override
    public Car createCar(Car car) {
        return repo.save(car);
    }

    @Override
    public List<Car> readAllCar() {
        return repo.findAll();
    }

    @Override
    public Car updateCar(Car car) {
        return repo.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAllCars() {
        repo.deleteAll();
    }
}
