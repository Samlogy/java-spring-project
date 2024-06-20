package com.example.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Orderr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "order_date")
    private LocalDate orderDate;

//    @OneToMany(mappedBy = "orderr", cascade = CascadeType.ALL, orphanRemoval=true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
}


// ALTER TABLE orderr ADD COLUMN customer_id INT;
// ALTER TABLE orderr ADD CONSTRAINT fk_order_user FOREIGN KEY (customer_id) REFERENCES customer(id);