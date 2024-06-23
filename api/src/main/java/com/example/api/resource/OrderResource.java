package com.example.api.resource;

import com.example.api.dto.Order.CreateOrderRequestDto;
import com.example.api.model.Orderr;
import com.example.api.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


//@Tag(name = "Orders", description = "Orders ecommerce api v1.0.0")
@RestController
@RequestMapping("api/v1/order")
@Slf4j
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService ) {
        this.orderService = orderService;
    }

    @GetMapping("customer/{customerId}")
    public ResponseEntity<?> getAllOrders(@PathVariable Integer customerId){
        List<Orderr> response = orderService.getAllOrders(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{orderId}/customer/{customerId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Integer orderId,
                                         @PathVariable Integer customerId){
        orderService.deleteOrderByOrderId(orderId, customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto dto,
                                         @PathVariable Integer customerId){
        orderService.createOrder(dto, customerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}/customer/{customerId}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer orderId,
                                          @PathVariable Integer customerId) {
        Orderr order = orderService.getOrderById(orderId, customerId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}