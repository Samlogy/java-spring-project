package com.example.api.unit.Customer;

import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = Customer.builder().id(1).fullName("sam sam").build();
    }


    @Test
    public void whenGetCustomerById_thenThrowNotFoundException() {
        // given
        int nonExistentCustomerId = 999; // Assuming 999 is not an existing customer ID

        // when
        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> {
            customerService.getCustomerById(nonExistentCustomerId);
        });
    }


    @Test
    public void whenGetCustomerById_thenReturnExistingCustomer() {
        // given
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // when
        Optional<Customer> foundCustomerOpt = Optional.ofNullable(customerService.getCustomerById(customerId));

        // then
        assertTrue(foundCustomerOpt.isPresent());
        assertEquals(foundCustomerOpt.get().getId(), customerId);
    }

    @Test
    public void whenGetAllCustomers_thenReturnCustomerList() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        // when
        List<Customer> foundCustomers = customerService.getAllCustomers();

        // then
        assertEquals(foundCustomers.size(), 1);
        assertEquals(foundCustomers.get(0).getFullName(), "sam sam");
    }

    @Test
    public void whenCreateCustomer_thenReturnCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        // when
        Customer createdCustomer = customerService.createCustomer(customer);

        // then
        assertEquals(createdCustomer.getFullName(), "sam sam");
    }

    @Test
    public void whenDeleteCustomerById_thenReturnExistingCustomer() {
        // given
        int customerId = customer.getId();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepository).deleteById(customerId);

        // when
        customerService.deleteCustomer(customerId);

        // then
        verify(customerRepository, times(1)).findById(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    public void whenDeleteCustomerById_thenThrowNotFoundException() {
        // given
        int nonExistentCustomerId = 999;
        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> {
            customerService.deleteCustomer(nonExistentCustomerId);
        });

        verify(customerRepository, times(1)).findById(nonExistentCustomerId);
        verify(customerRepository, never()).deleteById(nonExistentCustomerId);
    }

}
