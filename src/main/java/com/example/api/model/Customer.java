package com.example.api.model;

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
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "full_name")
    private String fullName;

//    @Column(name = "email")
    private String email;

//    @Column(name = "phone")
    private int phone;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval=true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Product> proudcts = new ArrayList<Product>();
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval=true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Orderr> orders = new ArrayList<Orderr>();
//
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval=true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Shipping> shippings = new ArrayList<Shipping>();
}