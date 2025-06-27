package com.example.OrderManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCustomerNameException extends RuntimeException {
    public InvalidCustomerNameException() {
        super();
    }
    public InvalidCustomerNameException(String message) {
        super(message);
    }
}
