package ru.mart.andersen.creditor.util.exceptions;

public class NoSuitableInterestException extends RecordNotFoundException {
    public NoSuitableInterestException(String message) {
        super(message);
    }
}
