package com.example.api.bdd;

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
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setFullName("John Doe");
    }

    @Test
    public void whenGetCustomerById_thenReturnCustomer() {
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(customer.getId());

        assertEquals(foundCustomer.getId(), customer.getId());
        assertEquals(foundCustomer.getFullName(), customer.getFullName());
    }

    @Test
    public void whenGetNonExistingCustomerById_thenThrowNotFoundException() {
        int nonExistentCustomerId = 999;
        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> customerService.getCustomerById(nonExistentCustomerId));

        assertEquals("Customer does not exist with this ID: " + nonExistentCustomerId, exception.getMessage());
    }
}
