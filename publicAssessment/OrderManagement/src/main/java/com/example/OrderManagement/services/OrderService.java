package com.example.OrderManagement.services;

import com.example.OrderManagement.models.OrderLineOrder;
import com.example.OrderManagement.models.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getOrderById(long orderId);

    long placeOrder(OrderDTO orderDTO);

    void deleteOrder(long orderId);

    void updateOrder(long orderId, OrderDTO orderDTO);

    void processOrders();
}
