package ru.mart.andersen.creditor.util.exceptions;

public class NoSuchUserException extends RecordNotFoundException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
