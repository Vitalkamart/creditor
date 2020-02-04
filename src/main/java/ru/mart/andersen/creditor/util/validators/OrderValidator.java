package ru.mart.andersen.creditor.util.validators;

import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.util.exceptions.ItemValidationException;
import ru.mart.andersen.creditor.util.exceptions.OrderValidationException;
import ru.mart.andersen.creditor.util.exceptions.UserValidationException;

import java.util.List;
import java.util.Objects;

import static ru.mart.andersen.creditor.util.ValidationUtil.*;

public class OrderValidator {
    public static void validateOrder(Order order) {
        try {
            Objects.requireNonNull(order, "can't be null");
//            validateStringId(order.getId());
            validatePrice(order.getPrice());
            validateDiscount(order.getDiscount());

            UserValidator.validateLogin(order.getUser().getLogin());
            validateName(order.getUser().getName());

            List<Item> items = order.getOrderItems();
            validateItemList(items);

            for (Item it : items) {
                ItemValidator.validateItem(it);
            }
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new OrderValidationException("order " + ex.getMessage());
        } catch (UserValidationException ex) {
            throw new OrderValidationException("in order with id " + order.getUid()  +
                    " " + ex.getMessage());
        } catch (ItemValidationException ex) {
            throw new OrderValidationException("order with id " + order.getUid() +
                    " " + ex.getMessage());
        }
    }

    private static void validateItemList(List<Item> items) {
        Objects.requireNonNull(items, "order item list can't be null");

        if (items.isEmpty()) {
            throw new OrderValidationException("item list can't be empty");
        }
    }
}
