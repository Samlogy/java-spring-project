package com.example.api.unit;

import com.example.api.model.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void whenGetCustomerById_thenThrowNotFoundException() {
        // given
        int nonExistentCustomerId = 999; // Assuming 999 is not an existing customer ID

        // when
        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(Optional.empty());

        // then
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            customerService.getCustomerById(nonExistentCustomerId);
        });
    }


    @Test
    public void whenGetCustomerById_thenReturnExistingCustomer() {
        // given
        int customerId = 1;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // when
        Optional<Customer> foundCustomerOpt = customerService.getCustomerById(customerId);

        // then
        assertTrue(foundCustomerOpt.isPresent()); // Ensure Optional is not empty
        assertEquals(foundCustomerOpt.get().getId(), customerId);
    }

    @Test
    public void whenGetAllCustomers_thenReturnCustomerList() {
        // given
        Customer customer = new Customer();
        customer.setFullName("sam sam");
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
        // given
        Customer customer = new Customer();
        customer.setFullName("sam sam");
        when(customerRepository.save(customer)).thenReturn(customer);

        // when
        Customer createdCustomer = customerService.createCustomer(customer);

        // then
        assertEquals(createdCustomer.getFullName(), "sam sam");
//        verify(customerRepository, times(1)).save(customer);
    }
}
