package ru.mart.andersen.creditor.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mart.andersen.creditor.TestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.*;

class ValidationUtilTest {

    @ParameterizedTest
    @ValueSource(ints = {BAD_CREDIT_RATE_LOW, BAD_CREDIT_RATE_HIGH})
    @DisplayName("bad credit rate")
    void validateCreditRate(int a) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateCreditRate(a) );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 121})
    @DisplayName("bad period")
    void validatePeriod(int a) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validatePeriod(a) );
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"0", "-1"})
    @DisplayName("bad amount")
    void validateAmount(String s) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateAmount(new BigDecimal(s)) );
    }

    @Test
    @DisplayName("amount is null")
    void validateAmountIsNull() {
        assertThrows(NullPointerException.class, () -> ValidationUtil.validateAmount(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1"})
    @DisplayName("bad price")
    void validatePrice(String s) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validatePrice(new BigDecimal(s)) );
    }

    @Test
    @DisplayName("price is null")
    void validatePriceIsNull() {
        assertThrows(NullPointerException.class, () -> ValidationUtil.validatePrice(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 101})
    @DisplayName("bad discount")
    void validateDiscount(int a) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateDiscount(a) );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {SYMBOLS_256})
    @DisplayName("bad string id")
    void validateStringId(String id) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateStringId(id) );
    }

    @Test
    @DisplayName("bad uid")
    void validateUid() {
        assertThrows(NullPointerException.class, () -> ValidationUtil.validateUid(null) );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {SYMBOLS_4})
    @DisplayName("bad name")
    void validateName(String s) {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateName(s) );
    }

    @Test
    @DisplayName("bad long id")
    void validateLongId() {
        assertThrows(IllegalArgumentException.class, () -> ValidationUtil.validateLongId(0L) );
    }

    @Test
    @DisplayName("long id is null")
    void validateLongIdIsNull() {
        assertThrows(NullPointerException.class, () -> ValidationUtil.validateLongId(null) );
    }
}