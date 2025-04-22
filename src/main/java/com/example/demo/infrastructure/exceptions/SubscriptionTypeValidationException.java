package com.example.demo.infrastructure.exceptions;

public class SubscriptionTypeValidationException extends RuntimeException {
    public SubscriptionTypeValidationException(String message) {
        super(message);
    }
}