package ru.mart.andersen.creditor.util;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ValidationUtil {
    public static void validateCreditRate(int creditRate) {
        if (creditRate < 50 || creditRate > 240) {
            throw new IllegalArgumentException("credit rate out of range: min = 5% (50), max = 24% (240)");
        }
    }

    public static void validatePeriod(int period) {
        if (period < 1 || period > 120) {
            throw new IllegalArgumentException("period out of range: min = 1, max = 120 (months)");
        }
    }

    public static void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("credit amount can't be <= 0");
        }
    }

    public static void validatePrice(BigDecimal price) {
        Objects.requireNonNull(price);
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("discount can't be < 0");
        }
    }

    public static void validateDiscount(int discountInt) {
        if (discountInt > 100 || discountInt < 0) {
            throw new IllegalArgumentException("discount can't be out of range 0-100");
        }
    }

    public static void validateStringId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id can't be null or empty");
        } else if (id.length() > 255) {
            throw new IllegalArgumentException("255 symbols is a maximum length of id");
        }
    }

    public static void validateUid(UUID uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid can't be null");
        }
    }

    public static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name can't be empty or null");
        } else if (name.length() < 4) {
            throw new IllegalArgumentException("name should contain at least 5 symbols");
        }
    }
    public static void validateLongId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id can't be null or negative");
        }
    }
}
