package ru.mart.andersen.creditor.util.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.util.exceptions.OrderValidationException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.TestUtils.SYMBOLS_4;
import static ru.mart.andersen.creditor.TestUtils.getTestOrder;
import static ru.mart.andersen.creditor.util.validators.OrderValidator.validateOrder;

class OrderValidatorTest {
    private static Order testOrder;

    @BeforeEach
    void init() {
        testOrder = getTestOrder();
    }

    @Test
    @DisplayName("is null")
    void validateOrderIsNull() {
        assertThrows(OrderValidationException.class, () -> validateOrder(null));
    }

    @Test
    @DisplayName("bad price")
    void validateOrderWithBadPrice() {
        testOrder.setPrice(null);

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }

    @Test
    @DisplayName("bad discount")
    void validateOrderWithBadDiscount() {
        testOrder.setDiscount(4);

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }

    @Test
    @DisplayName("bad user login")
    void validateOrderWithBadUserLogin() {
        testOrder.getUser().setLogin(SYMBOLS_4);

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }

    @Test
    @DisplayName("bad user name")
    void validateOrderWithBadUserName() {
        testOrder.getUser().setName(SYMBOLS_4);

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }

    @Test
    @DisplayName("bad item list")
    void validateOrderWithBadItemList() {
        List<Item> items = new ArrayList<>();
        items.add(null);
        testOrder.setOrderItems(items);

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }

    @Test
    @DisplayName("item list is null")
    void validateItemListIsNull() {
        testOrder.setOrderItems(null);

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }

    @Test
    @DisplayName("item list is empty")
    void validateItemListIsEmpty() {
        testOrder.setOrderItems(new ArrayList<>());

        assertThrows(OrderValidationException.class, () -> validateOrder(testOrder));
    }
}