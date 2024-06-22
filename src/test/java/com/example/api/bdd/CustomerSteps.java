package com.example.api.bdd;

import com.example.api.exception.NotFoundException;
import com.example.api.model.Customer;
import com.example.api.repository.CustomerRepository;
import com.example.api.service.CustomerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerSteps {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private Exception exception;

    @Given("a customer exists with ID {int}")
    public void aCustomerExistsWithID(int id) {
        customer = new Customer();
        customer.setId(id);
        customer.setFullName("John Doe");
        customerRepository.save(customer);
    }

    @Given("no customer exists with ID {int}")
    public void noCustomerExistsWithID(int id) {
        customerRepository.deleteById(id);
    }

    @When("the client retrieves the customer by ID {int}")
    public void theClientRetrievesTheCustomerByID(int id) {
        try {
            customer = customerService.getCustomerById(id);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the client receives the customer with ID {int} and name {string}")
    public void theClientReceivesTheCustomerWithIDAndName(int id, String name) {
        assertEquals(id, customer.getId());
        assertEquals(name, customer.getFullName());
    }

    @Then("the client receives a NotFoundException with message {string}")
    public void theClientReceivesANotFoundExceptionWithMessage(String message) {
        assertEquals(NotFoundException.class, exception.getClass());
        assertEquals(message, exception.getMessage());
    }
}

