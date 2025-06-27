package com.example.LoggingService.services.impl;

import com.example.LoggingService.docs.Orders;
import com.example.LoggingService.docs.dtos.OrderDTO;
import com.example.LoggingService.repository.OrderRepository;
import com.example.LoggingService.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void logOrder(OrderDTO orderDTO) {

        Orders order = new Orders.Builder().orderId(orderDTO.getOrderId())
                .orderDate(orderDTO.getOrderDate())
                .amount(orderDTO.getAmount())
                .itemsCount(orderDTO.getItemsCount())
                .build();

        
        orderRepository.save(order);
    }

}
