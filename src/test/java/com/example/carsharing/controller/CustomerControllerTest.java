package com.example.carsharing.controller;


import com.example.carsharing.model.Customer;
import com.example.carsharing.service.CustomerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @Test
    public void testReadAllCustomers(){
        Customer customer1 = new Customer();
        customer1.setName("Sergey");
        customer1.setCustomerId(1L);

        Customer customer2 = new Customer();
        customer2.setName("Kirill");
        customer2.setCustomerId(2L);

        when(customerService.readAllCustomer()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerController.readAllCustomers();
        assertEquals(2, customers.size());
        assertEquals("Sergey", customers.get(0).getName());
    }
}
