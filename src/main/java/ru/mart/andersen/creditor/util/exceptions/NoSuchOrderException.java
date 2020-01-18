package ru.mart.andersen.creditor.util.exceptions;

public class NoSuchOrderException extends RuntimeException {
    public NoSuchOrderException(String message) {
        super(message);
    }
}
