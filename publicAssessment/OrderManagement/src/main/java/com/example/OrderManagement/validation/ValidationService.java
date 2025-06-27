package com.example.OrderManagement.validation;

import com.example.OrderManagement.models.dtos.OrderDTO;

public interface ValidationService {
    void validateOrder(OrderDTO orderDTO);
}
