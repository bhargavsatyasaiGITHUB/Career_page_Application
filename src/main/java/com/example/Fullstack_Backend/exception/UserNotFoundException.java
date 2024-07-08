package com.example.Fullstack_Backend.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
