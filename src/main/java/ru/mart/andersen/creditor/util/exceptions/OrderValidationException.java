package ru.mart.andersen.creditor.util.exceptions;

public class OrderValidationException extends RuntimeException {
    public OrderValidationException(String message) {
        super(message);
    }
}
