package com.example.api.service;


import com.example.api.dto.Customer.CreateCustomerDto;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer does not exist with this ID: " + customerId));
    }

    public void createCustomer(CreateCustomerDto dto) {
        // uni-directional relationship (Customer)
        Customer newCustomer = Customer.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
        customerRepository.save(newCustomer);

        // bi-directional relationship
//        Customer newCustomer = Customer.builder()
//                .fullName(dto.getFullName())
//                .email(dto.getEmail())
//                .phone(dto.getPhone())
//                .address(dto.getAddress())
//                .build();
//        if (newCustomer.getAddress() != null) {
//            newCustomer.getAddress().setCustomer(newCustomer);
//        }
//        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer does not exist with this ID: " + customerId));
        customerRepository.deleteById(customerId);
    }

}