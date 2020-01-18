package ru.mart.andersen.creditor.util.validators;

import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.util.exceptions.ItemValidationException;
import ru.mart.andersen.creditor.util.exceptions.OrderValidationException;
import ru.mart.andersen.creditor.util.exceptions.UserValidationException;

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
            for (Item it :order.getOrderItems()) {
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
}
