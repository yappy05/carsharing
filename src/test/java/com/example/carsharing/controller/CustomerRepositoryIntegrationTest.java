package com.example.carsharing.controller;

import com.example.carsharing.model.Customer;
import com.example.carsharing.repository.CustomerRepository;
import com.example.carsharing.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class  CustomerRepositoryIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;
    private Long testCustomerId;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        // Очищаем базу перед каждым тестом
        customerRepository.deleteAll();

        // Создаем тестового customer
        testCustomer = new Customer();
        testCustomer.setName("Test");
        testCustomer.setLastName("User");
        testCustomer.setLogin("testuser");
        testCustomer.setPassword("password");
        testCustomer.setAdress("123 Main St");
        testCustomer.setDrivingExperience(2);
        testCustomer.setSubscribe(true);
        testCustomer.setHasDrivingLicense(true);

        // Сохраняем и запоминаем ID
        testCustomer = customerRepository.save(testCustomer);
        testCustomerId = testCustomer.getCustomerId();
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        String customerJson = """
            {
                "name": "Test",
                "lastName": "User",
                "login": "testuser",
                "password": "password",
                "adress": "123 Test St",
                "drivingExperience": 3,
                "isSubscribe": true,
                "hasDrivingLicense": true
            }
            """;

        // Выполняем запрос и проверяем статус и поля
        var result = mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"));

        // Если нужно сравнить весь JSON ответа
        String responseContent = result.andReturn().getResponse().getContentAsString();
        Customer responseCustomer = objectMapper.readValue(responseContent, Customer.class);

        // Проверяем отдельные поля вместо всего JSON
        assertEquals("Test", responseCustomer.getName());
        assertEquals("User", responseCustomer.getLastName());
    }
    @Test
    public void shouldReadAllCustomers() throws Exception {
        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].customerId").value(testCustomerId))
                .andExpect(jsonPath("$[0].name").value("Test"))
                .andExpect(jsonPath("$[0].hasDrivingLicense").value(true));
    }
//    @Test
//    public void shouldUpdateCustomer() throws Exception {
//        String updatedCustomerJson = """
//            {
//                "id": %d,
//                "name": "Updated",
//                "lastName": "User",
//                "login": "updateduser",
//                "password": "newpassword",
//                "adress": "456 Updated St",
//                "drivingExperience": 5,
//                "isSubscribe": false,
//                "hasDrivingLicense": true
//            }
//            """.formatted(testCustomerId);
//
//        mockMvc.perform(put("/api/customer")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(updatedCustomerJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Updated"))
//                .andExpect(jsonPath("$.adress").value("456 Updated St"));
//
//
//        // Проверяем обновление в БД
//        Customer updated = customerRepository.findById(testCustomerId).orElseThrow();
//        assertEquals("Updated", updated.getName());
//        assertEquals("456 Updated St", updated.getAdress());
//    }
    @Test
    public void shouldDeleteCustomerById() throws Exception {
        mockMvc.perform(delete("/api/customer/delete/{id}", testCustomerId))
                .andExpect(status().isOk());

        // Проверяем, что запись удалилась
        assertEquals(0, customerRepository.count());
        assertFalse(customerRepository.existsById(testCustomerId));
    }

}