package com.example.api.resource;

import com.example.api.dto.CreateOrderItemRequestDto;
import com.example.api.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/orderItem")
@Slf4j
public class OrderItemResource {
    private final OrderItemService orderItemService;

    public OrderItemResource(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }


    @PostMapping("/{orderId}")
    public ResponseEntity<?> addOrderItem(@RequestBody CreateOrderItemRequestDto requestDto,
                                          @PathVariable Integer orderId) {
        orderItemService.addOrderItem(requestDto, orderId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @DeleteMapping("/{orderId}")
//    public ResponseEntity<?> deleteOrderItem(@PathVariable Integer orderId) {
////        orderItemService.deleteOrderItem(orderId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}