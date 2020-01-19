package ru.mart.andersen.creditor.util.exceptions;

public class NoSuchCreditOfferException extends RecordNotFoundException {
    public NoSuchCreditOfferException(String message) {
        super(message);
    }
}
