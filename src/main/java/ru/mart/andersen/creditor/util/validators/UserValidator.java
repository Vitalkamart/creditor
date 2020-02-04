package ru.mart.andersen.creditor.util.validators;

import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.util.exceptions.UserValidationException;

import static ru.mart.andersen.creditor.util.ValidationUtil.validateName;

public class UserValidator {
    public static void validateUser(User user) {
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        try{
            validateName(user.getName());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UserValidationException("user " + ex.getMessage());
        }
    }

    protected static void validateLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new UserValidationException("user login can't be null or empty");
        }

        int length = login.length();
        if (length < 5 || length > 100) {
            throw new UserValidationException("user login should contain from 5 to 100 symbols");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new UserValidationException("user password can't be empty or null");
        }

        int length = password.length();
        if (length < 5 || length > 25) {
            throw new UserValidationException("user password should contain from 5 to 25 symbols");
        }

    }

    protected static void validateId(Long id) {
        if (id == null || id < 0) {
            throw new UserValidationException("user id can't be < 0 or null");
        }
    }
}
