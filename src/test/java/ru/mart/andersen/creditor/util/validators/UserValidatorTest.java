package ru.mart.andersen.creditor.util.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.util.exceptions.UserValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.*;

class UserValidatorTest {

    @Test
    @DisplayName("username is shorter than 4 symbols")
    void validateUser() {
        User user = USER_1;
        user.setName(SYMBOLS_4);

        assertThrows(UserValidationException.class, () -> UserValidator.validateUser(user));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {SYMBOLS_4, SYMBOLS_101})
    @DisplayName("bad login")
    void validateLogin(String login) {
        assertThrows(UserValidationException.class, () -> UserValidator.validateLogin(login));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {SYMBOLS_4, SYMBOLS_26})
    @DisplayName("bad password")
    void validatePassword(String password) {
        assertThrows(UserValidationException.class, () -> UserValidator.validatePassword(password));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {-1L})
    @DisplayName("bad id")
    void validateId(Long id) {
        assertThrows(UserValidationException.class, () -> UserValidator.validateId(id));
    }
}