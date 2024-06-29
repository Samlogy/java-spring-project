package com.example.api.service;

import com.example.api.dto.Order.CreateOrderRequestDto;
import com.example.api.exception.BadRequestionException;
import com.example.api.exception.NotFoundException;
import com.example.api.model.OrderItem;
import com.example.api.model.Orderr;
import com.example.api.repository.OrderRepository;
import jakarta.transaction.Transactional;
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

    public List<Orderr> getAllOrders(Integer customerId) {
        return orderRepository.findAll();
    }

    public void cancelOrder(Integer orderId, Integer customerId) {
        Orderr order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));

        orderRepository.deleteById(order.getId());
    }

    @Transactional
    public void deleteOrderByOrderId(Integer orderId,
                                     Integer customerId) {
        Orderr orderr = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + orderId));
        orderRepository.delete(orderr);
    }

    public void createOrder(CreateOrderRequestDto dto, Integer customerId) {
        // uni-directional => (Orderr)
//        Orderr newOrder = Orderr.builder()
//                .totalPrice(dto.getTotalPrice())
//                .status("PENDING")
//                .orderDate(LocalDate.now())
//                .orderItems(dto.getOrderItems())
//                .build();
//        orderRepository.save(newOrder);

        // bi-directional
        Orderr newOrder = Orderr.builder()
                .totalPrice(dto.getTotalPrice())
                .status("PENDING")
                .orderDate(LocalDate.now())
                .orderItems(dto.getOrderItems())
                .build();
        if (newOrder.getOrderItems() != null) {
            for (OrderItem item : newOrder.getOrderItems()) {
                item.setOrderr(newOrder);
            }
        }
        orderRepository.save(newOrder);
    }

    public Orderr getOrderById(Integer orderId, Integer customerId) {
        Orderr order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order does not exist with this ID: " + orderId));
//        List<OrderItem> items = orderItemRepository.findByOrderr(order);
//        order.setOrderItems(items);
        return order;
    }

}