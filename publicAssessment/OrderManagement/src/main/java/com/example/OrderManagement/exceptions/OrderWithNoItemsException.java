package com.example.OrderManagement.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderWithNoItemsException extends RuntimeException {
    public OrderWithNoItemsException() {
        super();
    }
    public OrderWithNoItemsException(String message) {
        super(message);
    }
}
