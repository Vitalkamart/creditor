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
    public static final User USER_1;
    public static final User USER_2;

    public static final Item ITEM_1;
    public static final Item ITEM_2;

    public static final String SYMBOLS_4 = "Name";
    public static final String SYMBOLS_26 = "Lorem ipsum dolor sit amet";
    public static final String SYMBOLS_101 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, " +
            "sed diam nonummy nibh euismod tincidunt ut ";
    public static final String SYMBOLS_256 = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, " +
            "sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim " +
            "ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea co";

    public static final int BAD_CREDIT_RATE_LOW = 49;
    public static final int BAD_CREDIT_RATE_HIGH = 241;

    static {
        ITEM_1 = new Item();
        ITEM_1.setPrice(new BigDecimal("100000.00"));
        ITEM_1.setName("диван");
        ITEM_1.setId("1");

        ITEM_2 = new Item();
        ITEM_2.setPrice(new BigDecimal("50000.00"));
        ITEM_2.setName("стул");
        ITEM_2.setId("2");

        USER_1 = new User();
        USER_1.setId(1L);
        USER_1.setName("User Userovich");
        USER_1.setLogin("login1");
        USER_1.setPassword("$2a$10$RK56TyFl9Td2WI7rXI/.0ue/KRMpe42HO/bv.DsQKDKp9EzSQxHUW");
        USER_1.setRole(Role.USER);

        USER_2 = new User();
        USER_2.setId(2L);
        USER_2.setName("Arnoldo Schwarzehfogel");
        USER_2.setLogin("login2");
        USER_2.setPassword("$2a$10$fkeDmtfaW7x9gpZm/Nh9guNtT8TT7J95xI7hJIbYV8.24e2E3mJ8G");
        USER_2.setRole(Role.USER);

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

        List<Item> itemList = new ArrayList<>();
        itemList.add(ITEM_1);
        itemList.add(ITEM_2);

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
        order.setUser(USER_1);

        List<Item> items = new ArrayList<>();
        items.add(ITEM_1);
        items.add(ITEM_2);
        items.add(ITEM_2);
        items.add(ITEM_2);

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
        order.setUser(USER_2);

        List<Item> items = new ArrayList<>();
        items.add(ITEM_1);

        order.setOrderItems(items);

        creditOffer.setOrder(order);
        creditOffer.setUserName(USER_2.getLogin());
        creditOffer.setAmount(new BigDecimal("90000.00"));
        creditOffer.setPeriod(10);

        return creditOffer;
    }
}
