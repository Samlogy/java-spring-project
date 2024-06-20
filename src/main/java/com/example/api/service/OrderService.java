package com.example.api.service;

import com.example.api.dto.Order.CreateOrderRequestDto;
import com.example.api.exception.BadRequestionException;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Orderr;
import com.example.api.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    //    Map<String, Object> GetOrdersRequestDto requestDto
    public List<Orderr> getAllOrders(Integer customerId) {
        return orderRepository.findAll();
    }

    public void cancelOrder(Integer orderId, Integer customerId) {
        Orderr order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));

        orderRepository.deleteById(order.getId());
//
//        LocalDate currentDate = LocalDate.now();
//        LocalDate orderDate = order.getOrderDate();
//        LocalDate deadLineDate = orderDate.plusDays(2);
//
//        if (currentDate.isBefore(deadLineDate) || currentDate.isEqual(deadLineDate)) {
//            orderItemRepository.deleteByOrderr(order);
//            orderRepository.deleteById(orderId);
//            return 1;
//        }
//        throw new BadRequestionException("Order date is more than 2 days old can not cancel this order !");
    }

    public void confirmOrder(Integer orderId, Integer customerId) {
        Orderr orderUpdate = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));

        orderUpdate.setStatus("CONFIRMED");
        orderRepository.save(orderUpdate);

//        LocalDate currentDate = LocalDate.now();
//        LocalDate orderDate = orderUpdate.getOrderDate();
//        LocalDate deadLineDate = orderDate.plusDays(2);
//
//        if (currentDate.isBefore(deadLineDate) || currentDate.isEqual(deadLineDate)) {
//            orderUpdate.setStatus("CONFIRMED");
//            return orderRepository.save(orderUpdate).getId();
//        }
//        throw new BadRequestionException("Order date is more than 2 days old, it was confirmed by default !");
    }

    public void createOrder(CreateOrderRequestDto requestDto, Integer customerId) {
        Orderr newOrder = Orderr.builder()
                .totalPrice(requestDto.getTotalPrice())
                .status("PENDING")
                .orderDate(LocalDate.now())
                .build();
        Orderr orderAdded = orderRepository.save(newOrder);

//        List<OrderItem> orderItems = requestDto.getOrderItems();
//
//        List<OrderItem> orderItemsUpdated = orderItems.stream().map(item -> {
//            item.setOrderr(orderAdded);
//            return item;
//        }).collect(Collectors.toList());
//        orderItemRepository.saveAll(orderItemsUpdated);
    }

    public Orderr getOrderById(Integer orderId, Integer customerId) {
        Orderr order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));
//        List<OrderItem> items = orderItemRepository.findByOrderr(order);
//        order.setOrderItems(items);
        return order;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }
}