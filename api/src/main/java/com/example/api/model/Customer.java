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

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private int phone;

    @OneToOne(cascade = CascadeType.ALL) // => uni-directional relationship
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

//    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL) => bi-directional relationship
//    private Address address;
}