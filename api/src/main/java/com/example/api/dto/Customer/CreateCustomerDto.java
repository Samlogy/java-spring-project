package com.example.api.dto.Customer;


import com.example.api.model.Address;
import lombok.*;

@Getter
@Setter
public class CreateCustomerDto {
    private String fullName;
    private String email;
    private int phone;
    private Address address;
}
