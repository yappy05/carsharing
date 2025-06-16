package com.example.carsharing.controller;

import com.example.carsharing.model.Customer;
import com.example.carsharing.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class  CustomerRepositoryIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    private CustomerService customerService;

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
}