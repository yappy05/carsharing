package com.example.carsharing.service;

import com.example.carsharing.model.Customer;
import com.example.carsharing.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository repo;
    @Override
    public List<Customer> readAllCustomer() {
        return repo.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }
}
