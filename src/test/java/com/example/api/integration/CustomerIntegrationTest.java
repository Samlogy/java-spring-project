package com.example.api.integration;

import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CustomerIntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @AfterEach
    public void cleanUp() {
        customerRepository.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        customer = customerRepository.save(Customer.builder().fullName("sam sam").build());
    }

    @Test
    public void whenSaveCustomer_thenCustomerIsPersisted() {
        // then
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getFullName()).isEqualTo("sam sam");

        // Check if customer is saved in the repository
        List<Customer> allCustomers = customerRepository.findAll();
        assertThat(allCustomers).hasSize(1);
        assertThat(allCustomers.get(0)).isEqualTo(customer);
    }

    @Test
    public void whenGetCustomerById_thenRetrieveCustomer() {
        // given
        Customer customer = new Customer();
        customer.setFullName("Jane Smith");
        Customer savedCustomer = customerRepository.save(customer);

        // when
        Customer retrievedCustomer = customerService.getCustomerById(savedCustomer.getId());

        // then
        assertThat(retrievedCustomer).isNotNull();
        assertThat(retrievedCustomer.getId()).isEqualTo(savedCustomer.getId());
        assertThat(retrievedCustomer.getFullName()).isEqualTo("Jane Smith");
    }

    @Test
    public void whenGetNonExistingCustomerById_thenThrowNotFoundException() {
        // given
        int nonExistentCustomerId = 999; // Assuming this ID does not exist in the database

        // when
        Throwable exception = assertThrows(NotFoundException.class, () -> customerService.getCustomerById(nonExistentCustomerId));

        // then
        assertThat(exception.getMessage()).isEqualTo("Customer does not exist with this ID: " + nonExistentCustomerId);
    }

    @Test
    public void whenDeleteCustomerById_thenCustomerIsDeleted() {
        // when
        customerService.deleteCustomer(customer.getId());

        // then
        assertThat(customerRepository.findById(customer.getId())).isEmpty();
    }

    @Test
    public void whenDeleteNonExistingCustomer_thenThrowNotFoundException() {
        // given
        int nonExistentCustomerId = 999; // Assuming this ID does not exist in the database

        // when
        Throwable exception = assertThrows(NotFoundException.class, () -> customerService.deleteCustomer(nonExistentCustomerId));

        // then
        assertThat(exception.getMessage()).isEqualTo("Customer does not exist with this ID: " + nonExistentCustomerId);
    }
}