package ru.mart.andersen.creditor.util.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.util.exceptions.CreditOfferValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.BAD_CREDIT_RATE_LOW;
import static ru.mart.andersen.creditor.TestUtils.getTestCreditOffer;
import static ru.mart.andersen.creditor.util.validators.CreditOfferValidator.validateCreditOffer;

class CreditOfferValidatorTest {

    @Test
    @DisplayName("is null")
    void validateCreditOfferIsNull() {
        assertThrows(CreditOfferValidationException.class, () -> validateCreditOffer(null));
    }

    @Test
    @DisplayName("bad amount")
    void validateCreditOfferWithBadAmount() {
        CreditOffer creditOffer = getTestCreditOffer();
        creditOffer.setAmount(null);

        assertThrows(CreditOfferValidationException.class, () -> validateCreditOffer(creditOffer));
    }

    @Test
    @DisplayName("bad creditRate")
    void validateCreditOfferWithBadCreditRate() {
        CreditOffer creditOffer = getTestCreditOffer();
        creditOffer.setCreditRate(BAD_CREDIT_RATE_LOW);

        assertThrows(CreditOfferValidationException.class, () -> validateCreditOffer(creditOffer));
    }

    @Test
    @DisplayName("bad period")
    void validateCreditOfferWithBadPeriod() {
        CreditOffer creditOffer = getTestCreditOffer();
        creditOffer.setPeriod(0);

        assertThrows(CreditOfferValidationException.class, () -> validateCreditOffer(creditOffer));
    }
}