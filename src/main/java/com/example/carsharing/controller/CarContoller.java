package com.example.carsharing.controller;

import com.example.carsharing.model.Car;
import com.example.carsharing.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarContoller {
    private final CarService carService;
    @PostMapping("")
    Car createCar (@RequestBody Car car){
        return carService.createCar(car);
    }
    @GetMapping("")
    List<Car> readAllCars () {
        return carService.readAllCar();
    }
    @PutMapping("/update")
    Car updateCar (@RequestBody Car car){
        return carService.updateCar(car);
    }
    @DeleteMapping("/delete/id")
    HttpStatus deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return HttpStatus.OK;
    }
    @DeleteMapping("/delete/all")
    HttpStatus deleteAllCars(){
        carService.deleteAllCars();
        return HttpStatus.OK;
    }
}
