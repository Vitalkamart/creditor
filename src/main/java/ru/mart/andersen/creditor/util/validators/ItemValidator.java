package ru.mart.andersen.creditor.util.validators;

import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.util.exceptions.ItemValidationException;

import static ru.mart.andersen.creditor.util.ValidationUtil.*;

public class ItemValidator {
    public static void validateItem(Item item) {
        try {
            validatePrice(item.getPrice());
            validateStringId(item.getId());
            validateName(item.getName());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new ItemValidationException("item " + ex.getMessage());
        }
    }
}
