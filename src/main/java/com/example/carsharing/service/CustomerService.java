package com.example.carsharing.service;

import com.example.carsharing.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> readAllCustomer();
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
    void deleteAllCustomers();
}
