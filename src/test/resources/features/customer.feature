Feature: Customer Management

  Scenario: Get existing customer by ID
    Given a customer exists with ID 1
    When the client retrieves the customer by ID 1
    Then the client receives the customer with ID 1 and name "John Doe"

  Scenario: Get non-existing customer by ID
    Given no customer exists with ID 999
    When the client retrieves the customer by ID 999
    Then the client receives a NotFoundException with message "Customer does not exist with this ID: 999"
