package com.example.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

//    @OneToOne // => uni-directional relationship
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
}