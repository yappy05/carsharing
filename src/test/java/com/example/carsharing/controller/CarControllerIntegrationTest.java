package com.example.carsharing.controller;

import com.example.carsharing.model.Car;
import com.example.carsharing.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarRepository carRepository;

    private Car testCar;
    private Long testCarId;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();

        testCar = new Car();
        testCar.setBrend("Toyota");
        testCar.setModel("Camry");
        testCar.setSpeed(180);
        testCar.setMileage(50000);
        testCar.setLicensePlate("A123BC");
        testCar.setFuelConsumption(8);
        testCar.setPricePerMinute(2);
        testCar.setMaintenance("2023-10-01");
        testCar.setLocation("Parking 1");
        testCar.setLevelcomfort("Premium");
        testCar.setBooking(false);

        testCar = carRepository.save(testCar);
        testCarId = testCar.getCarId();
    }

    @Test
    public void shouldCreateCar() throws Exception {
        String carJson = """
            {
                "brend": "Honda",
                "model": "Accord",
                "speed": 200,
                "mileage": 30000,
                "licensePlate": "B456CD",
                "fuelConsumption": 7,
                "pricePerMinute": 3,
                "maintenance": "2023-09-15",
                "location": "Parking 2",
                "levelcomfort": "Standard",
                "isBooking": false
            }
            """;

        mockMvc.perform(post("/api/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brend").value("Honda"))
                .andExpect(jsonPath("$.model").value("Accord"));
    }

    @Test
    public void shouldReadAllCars() throws Exception {
        mockMvc.perform(get("/api/car"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brend").value("Toyota"))
                .andExpect(jsonPath("$[0].model").value("Camry"));
    }

    @Test
    public void shouldUpdateCar() throws Exception {
        String updatedCarJson = """
            {
                "carId": %d,
                "brend": "Toyota",
                "model": "Camry XLE",
                "speed": 190,
                "mileage": 55000,
                "licensePlate": "A123BC",
                "fuelConsumption": 9,
                "pricePerMinute": 3,
                "maintenance": "2023-11-01",
                "location": "Parking 3",
                "levelcomfort": "Luxury",
                "isBooking": true
            }
            """.formatted(testCarId);

        mockMvc.perform(put("/api/car/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedCarJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Camry XLE"))
                .andExpect(jsonPath("$.pricePerMinute").value(3));

        // Verify in database
        Car updatedCar = carRepository.findById(testCarId).orElseThrow();
        assertEquals("Camry XLE", updatedCar.getModel());
        assertEquals(3, updatedCar.getPricePerMinute());
    }

//    @Test
//    public void shouldDeleteCarById() throws Exception {
//        mockMvc.perform(delete("/api/car/delete/{id}", testCarId))
//                .andExpect(status().isOk());
//
//        assertFalse(carRepository.existsById(testCarId));
//    }

    @Test
    public void shouldDeleteAllCars() throws Exception {
        // Add another car
        Car anotherCar = new Car();
        anotherCar.setBrend("BMW");
        anotherCar.setModel("X5");
        carRepository.save(anotherCar);

        mockMvc.perform(delete("/api/car/delete/all"))
                .andExpect(status().isOk());

        assertEquals(0, carRepository.count());
    }
}