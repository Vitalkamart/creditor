package ru.mart.andersen.creditor;

import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.model.enums.Role;
import ru.mart.andersen.creditor.to.CartTo;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.to.UserTo;

import java.math.BigDecimal;
import java.util.*;

public class TestUtils {
    private static User user1;
    private static User user2;

    private static Item item1;
    private static Item item2;

    static {
        item1 = new Item();
        item1.setPrice(new BigDecimal("100000.00"));
        item1.setName("диван");
        item1.setId("1");

        item2 = new Item();
        item2.setPrice(new BigDecimal("50000.00"));
        item2.setName("стул");
        item2.setId("2");

        user1 = new User();
        user1.setId(1L);
        user1.setName("User Userovich");
        user1.setLogin("login1");
        user1.setPassword("$2a$10$RK56TyFl9Td2WI7rXI/.0ue/KRMpe42HO/bv.DsQKDKp9EzSQxHUW");
        user1.setRole(Role.USER);

        user2 = new User();
        user2.setId(2L);
        user2.setName("Arnoldo Schwarzehfogel");
        user2.setLogin("login2");
        user2.setPassword("$2a$10$fkeDmtfaW7x9gpZm/Nh9guNtT8TT7J95xI7hJIbYV8.24e2E3mJ8G");
        user2.setRole(Role.USER);
    }

    public static OrderTo getTestOrderTo() {
        OrderTo testOrderTo = new OrderTo();

        testOrderTo.setId("1");
        testOrderTo.setUid(UUID.randomUUID());
        testOrderTo.setDiscount(0);
        testOrderTo.setPrice(new BigDecimal("150000.00"));

        UserTo userTo = new UserTo();
        userTo.setId(1L);
        userTo.setLogin("login1");
        userTo.setName("User Userovich");

        Set<Item> itemList = new HashSet<>();
        itemList.add(item1);
        itemList.add(item2);

        CartTo cartTo = new CartTo();
        cartTo.setUserTo(userTo);
        cartTo.setItems(itemList);

        testOrderTo.setCartTo(cartTo);

        return testOrderTo;
    }

    public static Order getTestOrder() {
        Order order = new Order();

        order.setPrice(new BigDecimal("250000.00"));
        order.setId("115");
        order.setDiscount(17);
        order.setUser(user1);

        Set<Item> items = new HashSet<>();
        items.add(item1);
        items.add(item2);
        items.add(item2);
        items.add(item2);

        order.setOrderItems(items);

        return order;
    }

    public static CreditOffer getTestCreditOffer() {
        CreditOffer creditOffer = new CreditOffer();
        Order order = new Order();

        order.setUid(UUID.fromString("02885d24-c297-4dd8-bf60-481175fa30a6"));
        order.setPrice(new BigDecimal("100000.00"));
        order.setId("225");
        order.setDiscount(10);
        order.setUser(user2);

        Set<Item> items = new HashSet<>();
        items.add(item1);

        order.setOrderItems(items);

        creditOffer.setOrder(order);
        creditOffer.setUserName(user2.getLogin());
        creditOffer.setAmount(new BigDecimal("90000.00"));
        creditOffer.setPeriod(10);

        return creditOffer;
    }
}
