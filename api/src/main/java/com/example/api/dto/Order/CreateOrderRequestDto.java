package com.example.api.dto.Order;

import com.example.api.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequestDto {
    private String status;
    private float totalPrice;
    private String orderDate;
    private List<OrderItem> orderItems;
}