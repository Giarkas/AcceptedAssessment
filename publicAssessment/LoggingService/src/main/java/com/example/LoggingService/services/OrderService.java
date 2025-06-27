package com.example.LoggingService.services;

import com.example.LoggingService.docs.dtos.OrderDTO;

public interface OrderService {
    void logOrder(OrderDTO orderDTO);
}
