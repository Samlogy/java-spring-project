package com.example.api.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenCreateOrder_thenStatus200() throws Exception {
        String orderJson = "{"
                + "\"status\":\"Pending\","
                + "\"totalPrice\":100.0,"
                + "\"orderDate\":\"2023-06-21\","
                + "\"orderItems\":["
                + "{\"name\":\"Product 1\",\"quantity\":2,\"price\":50.0},"
                + "{\"name\":\"Product 2\",\"quantity\":1,\"price\":50.0}"
                + "]"
                + "}";

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated());
    }
}