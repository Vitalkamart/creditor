package ru.mart.andersen.creditor.util.validators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.util.exceptions.ItemValidationException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.SYMBOLS_4;
import static ru.mart.andersen.creditor.TestUtils.ITEM_1;
import static ru.mart.andersen.creditor.util.validators.ItemValidator.validateItem;

class ItemValidatorTest {

    @Test
    @DisplayName("bad id")
    void validateItemWithBadId() {
        Item item = ITEM_1;
        item.setId(null);
        assertThrows(ItemValidationException.class, () -> validateItem(item));
    }

    @Test
    @DisplayName("bad name")
    void validateItemWithBadName() {
        Item item = ITEM_1;
        item.setName(SYMBOLS_4);
        assertThrows(ItemValidationException.class, () -> validateItem(item));
    }

    @Test
    @DisplayName("bad price")
    void validateItemWithBadPrice() {
        Item item = ITEM_1;
        item.setPrice(null);
        assertThrows(ItemValidationException.class, () -> validateItem(item));
    }
}