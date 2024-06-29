package com.example.api.resource;


import com.example.api.dto.Customer.CreateCustomerDto;
import com.example.api.service.CustomerService;
import com.example.api.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/customer")
@Slf4j
@Validated
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable Integer customerId){
        Customer res = customerService.getCustomerById(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<?> registerCustomer(@RequestBody CreateCustomerDto dto) {
        Customer newCustomer = customerService.createCustomer(dto);
        return ResponseEntity.ok(newCustomer);
    }
}