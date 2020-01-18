package ru.mart.andersen.creditor.to.converter;

import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.to.CartTo;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.to.UserTo;

import java.util.HashSet;
import java.util.Set;

import static ru.mart.andersen.creditor.to.converter.UserConverter.getUserFromTo;

public class OrderConverter {

    public static OrderTo getToFromOrder(Order order) {
        OrderTo orderTo = new OrderTo();

        orderTo.setId(order.getId());
        orderTo.setUid(order.getUid());
        orderTo.setPrice(order.getPrice());
        orderTo.setDiscount(order.getDiscount());

        CartTo cartTo = new CartTo();
        UserTo userTo = new UserTo(order.getUser());

        cartTo.setUserTo(userTo);
        cartTo.setItems(order.getOrderItems());

        orderTo.setCartTo(cartTo);
        return orderTo;
    }

    public static Order getOrderFromTo(OrderTo orderTo) {
        Order order = new Order();

        order.setId(orderTo.getId());
        order.setPrice(orderTo.getPrice());
        order.setDiscount(orderTo.getDiscount());

        UserTo userTo = orderTo.getCartTo().getUserTo();

        order.setUser(getUserFromTo(userTo));
        order.setOrderItems(orderTo.getCartTo().getItems());

        return order;
    }
}
