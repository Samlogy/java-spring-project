package com.example.api.unit;


import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.repository.CustomerRepository;

import com.example.api.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setFullName("sam sam");
    }

    @Test
    public void whenGetByIdCustomer_thenReturnCustomer() {
        // given
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        // when
        Customer foundCustomer = customerService.getCustomerById(customer.getId());

        // then
        assertEquals(foundCustomer.getId(), customer.getId());
        assertEquals(foundCustomer.getFullName(), "sam sam");
    }

    @Test
    public void whenGetByIdCustomer_thenThrowNotFoundException() {
        // given
        int nonExistentId = 999; // Assuming 999 is not an existing customer ID

        // when
        when(customerRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> {
            customerService.getCustomerById(nonExistentId);
        });
    }
}