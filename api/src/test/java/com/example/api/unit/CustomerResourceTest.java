package com.example.api.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.resource.CustomerResource;
import com.example.api.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = Customer.builder().id(1).fullName("sam sam").build();
    }

    @Test
    public void whenGetAllCustomers_thenReturnCustomerList() throws Exception {
        // given
        given(customerService.getAllCustomers()).willReturn(Arrays.asList(customer));

        // when, then
        mockMvc.perform(get("/api/v1/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value(customer.getFullName()));
    }

    @Test
    public void whenGetCustomerById_thenReturnCustomer() throws Exception {
        // given
        given(customerService.getCustomerById(customer.getId())).willReturn(customer);

        // when, then
        mockMvc.perform(get("/api/v1/customer/{customerId}", customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(customer.getFullName()));
    }

    @Test
    public void whenGetCustomerById_thenReturnNotFound() throws Exception {
        // given
        given(customerService.getCustomerById(anyInt())).willThrow(new NotFoundException("Customer not found"));

        // when, then
        mockMvc.perform(get("/api/v1/customer/{customerId}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenRegisterCustomer_thenReturnCustomer() throws Exception {
        // given
        given(customerService.createCustomer(any(Customer.class))).willReturn(customer);

        // when, then
        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fullName\": \"sam sam\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value(customer.getFullName()));
    }

    @Test
    public void whenDeleteCustomer_thenReturnNoContent() throws Exception {
        // given
        doNothing().when(customerService).deleteCustomer(customer.getId());

        // when, then
        mockMvc.perform(delete("/api/v1/customer/{customerId}", customer.getId()))
                .andExpect(status().isNoContent());
    }
}