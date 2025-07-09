package com.example.AcceptedAssessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSpecifierException extends RuntimeException {
    public InvalidSpecifierException(String s) {
        super(s);
    }
}
