package ru.mart.andersen.creditor.to;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.Item;
//import ru.mart.andersen.creditor.model.OrderItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderToTest {
    public static Marshaller marshaller;
    public static OrderTo testOrder;

    public static Unmarshaller unmarshaller;

    @BeforeAll
    static void init() {
        initMarshallers();
        initOrderTo();
    }

    static void initMarshallers() {
        try {
            JAXBContext context = JAXBContext.newInstance(OrderTo.class);
            //creating marshaller
            marshaller = context.createMarshaller();
            // for pretty-print XML in JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //creating unmarshaller
            unmarshaller = context.createUnmarshaller();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    static void initOrderTo() {
        testOrder = new OrderTo();

        testOrder.setId(1L);
        testOrder.setUid(UUID.randomUUID());
        testOrder.setDiscount(0);
        testOrder.setPrice(BigDecimal.valueOf(15000000));

        UserTo userTo = new UserTo();
        userTo.setId(1L);
        userTo.setLogin("login1");
        userTo.setName("User Userovich");

        Item item1 = new Item();
        item1.setPrice(BigDecimal.valueOf(10000000));
        item1.setName("шкаф");
        item1.setId(1L);

        Item item2 = new Item();
        item2.setPrice(BigDecimal.valueOf(5000000));
        item2.setName("стул");
        item2.setId(2L);

        List<Item> orderItemList = new ArrayList<>();
        orderItemList.add(item1);
        orderItemList.add(item2);

        CartTo cartTo = new CartTo();
        cartTo.setUserTo(userTo);
        cartTo.setOrderItems(orderItemList);

        testOrder.setCartTo(cartTo);
    }

    @Test
    void marshallingAndUnmarshalling() {
        try {
            StringWriter sw = new StringWriter();

            marshaller.marshal(testOrder, sw);

            StringReader sr = new StringReader(sw.toString());

            OrderTo unmarshalled = (OrderTo) unmarshaller.unmarshal(sr);
            sw.close();
            sr.close();

            marshaller.marshal(unmarshalled, System.out);
            assertEquals(testOrder.toString(), unmarshalled.toString());
        } catch(JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void itemUID() {
        for (int i = 0; i < 5; i++) {
            System.out.println(UUID.randomUUID());
        }
    }
}