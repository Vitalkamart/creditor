package ru.mart.andersen.creditor.util.exceptions;

public class NoSuchOrderException extends RecordNotFoundException {
    public NoSuchOrderException(String message) {
        super(message);
    }
}
