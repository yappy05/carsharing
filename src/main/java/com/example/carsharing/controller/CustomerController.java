package com.example.carsharing.controller;

import com.example.carsharing.model.Customer;
import com.example.carsharing.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService service;

    @GetMapping("")
    public List<Customer> readAllCustomers() {
        return service.readAllCustomer();
    }
    @PostMapping("")
    public Customer createCustomer(@RequestBody Customer customer){
        return service.createCustomer(customer);
    }
    @PutMapping("")
    public Customer updateCustomer(@RequestBody Customer customer){
        return service.updateCustomer(customer);
    }
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteCustomer(@PathVariable Long id){
        service.deleteCustomer(id);
        return HttpStatus.OK;
    }
}