package com.example.api.unit;

import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.resource.CustomerResource;
import com.example.api.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerResource.class)
public class CustomerResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerResource customerResource;

    @Test
    public void whenGetCustomerById_thenReturnCustomer() throws Exception {
        // given
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFullName("sam sam");

        // when
        when(customerService.getCustomerById(1)).thenReturn(customer);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customer.getId())))
                .andExpect(jsonPath("$.name", is(customer.getFullName())));
    }

    @Test
    public void whenGetCustomerById_thenReturnNotFound() throws Exception {
        // when
        when(customerService.getCustomerById(anyInt())).thenThrow(new NotFoundException("Customer does not exist with this ID: 1"));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Customer does not exist with this ID: 1")));
    }
}