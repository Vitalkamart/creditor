package ru.mart.andersen.creditor.util.validators;

import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.util.exceptions.CreditOfferValidationException;

import java.util.Objects;

import static ru.mart.andersen.creditor.util.ValidationUtil.*;

public class CreditOfferValidator {
    public static void validateCreditOffer(CreditOffer creditOffer) {
        try{
            Objects.requireNonNull(creditOffer, "credit offer can't be null");
            validateAmount(creditOffer.getAmount());
            validateCreditRate(creditOffer.getCreditRate());
            validatePeriod(creditOffer.getPeriod());
        } catch (NullPointerException ex) {
            throw new CreditOfferValidationException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new CreditOfferValidationException("credit offer " + ex.getMessage());
        }
    }
}
