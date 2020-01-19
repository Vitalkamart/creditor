package ru.mart.andersen.creditor.util.exceptions;

public class ApplicationValidationException extends RuntimeException {
    public ApplicationValidationException(String message) {
        super(message);
    }
}
