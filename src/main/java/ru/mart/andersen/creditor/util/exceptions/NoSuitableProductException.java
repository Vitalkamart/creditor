package ru.mart.andersen.creditor.util.exceptions;

public class NoSuitableProductException extends RecordNotFoundException {
    public NoSuitableProductException(String message) {
        super(message);
    }
}
