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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private float price;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
}

// ALTER TABLE product ADD COLUMN customer_id INT;
// ALTER TABLE product ADD CONSTRAINT fk_product_user FOREIGN KEY (customer_id) REFERENCES customer(id);
