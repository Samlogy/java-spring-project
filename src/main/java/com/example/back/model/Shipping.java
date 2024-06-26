package com.example.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
}


// ALTER TABLE shipping ADD COLUMN customer_id INT;
// ALTER TABLE shipping ADD CONSTRAINT fk_shipping_user FOREIGN KEY (customer_id) REFERENCES customer(id);