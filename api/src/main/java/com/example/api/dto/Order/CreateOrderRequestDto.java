package com.example.api.dto.Order;

import com.example.api.model.OrderItem;
import com.example.api.model.Product;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequestDto {
    @NotNull(message = "Status cannot be null")
    @Size(min = 1, max = 255, message = "Status must be between 1 and 255 characters")
    private String status;

    @NotNull(message = "Total price cannot be null")
    @Positive(message = "Total price must be positive")
    private Float totalPrice;

    @NotNull(message = "Order date cannot be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Order date must be in the format YYYY-MM-DD")
    private String orderDate;

    @NotNull(message = "Order items cannot be null")
    @Size(min = 1, message = "There must be at least one order item")
    private Set<@Valid OrderItem> orderItems;

    @NotNull(message = "Products cannot be null")
    @Size(min = 1, message = "There must be at least one product")
    private Set<@Valid Product> products;
}

