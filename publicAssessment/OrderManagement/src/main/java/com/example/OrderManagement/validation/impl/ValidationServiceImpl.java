package com.example.OrderManagement.validation.impl;

import com.example.OrderManagement.exceptions.InvalidCustomerNameException;
import com.example.OrderManagement.exceptions.InvalidOrderDateException;
import com.example.OrderManagement.exceptions.OrderWithNoItemsException;
import com.example.OrderManagement.models.dtos.OrderDTO;
import com.example.OrderManagement.models.dtos.OrderLineDTO;
import com.example.OrderManagement.validation.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateOrder(OrderDTO orderDTO) {
        nameValidated(orderDTO.getCustomerName());
        dateValidation(orderDTO.getOrderDate());
        orderLinesValidation(orderDTO.getOrderLines());
    }

    private void orderLinesValidation(List<OrderLineDTO> orderLines) {
        if (orderLines == null || orderLines.isEmpty()) {
            throw new OrderWithNoItemsException();
        }
    }

    private void dateValidation(Date orderDate) {
        if (orderDate == null)
            throw new InvalidOrderDateException("Order date cannot be null");
        if (orderDate.after(new Date()))
            throw new InvalidOrderDateException("Order date cannot be after current date");
    }

    private void nameValidated(String customerName) {
        if (customerName == null)
            throw new InvalidCustomerNameException("Customer name cannot be null");
        if (customerName.length() < 5)
            throw new InvalidCustomerNameException("Customer name must be at least 5 characters");
        if (customerName.length() > 20)
            throw new InvalidCustomerNameException("Customer name cannot be longer than 20 characters");
        if (customerName.contains("1") || customerName.contains("2") || customerName.contains("3") ||
                customerName.contains("4") || customerName.contains("5") || customerName.contains("6") ||
                customerName.contains("7") || customerName.contains("8") || customerName.contains("9") ||
                customerName.contains("0"))
            throw new InvalidCustomerNameException("Customer name cannot contain numbers");
        if (customerName.contains(" ") || customerName.contains("\t") || customerName.contains("\n") ||
        customerName.contains("\r") || customerName.contains("\'") || customerName.contains("\"") ||
        customerName.contains("<") || customerName.contains(">") || customerName.contains("!") ||
        customerName.contains("@") || customerName.contains("#") || customerName.contains("$") ||
        customerName.contains(".") || customerName.contains(",") || customerName.contains(":"))
            throw new InvalidCustomerNameException("Customer name cannot contain special characters");
    }
}
