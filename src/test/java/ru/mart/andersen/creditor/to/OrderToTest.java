package ru.mart.andersen.creditor.to;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.Item;
import ru.mart.andersen.creditor.model.OrderItem;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderToTest {
    public static final String sampleXml = "";
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
        testOrder.setUid("O-1");
        testOrder.setDiscount(0);
        testOrder.setPrice(15000000);

        UserTo userTo = new UserTo();
        userTo.setId(1L);
        userTo.setLogin("login1");
        userTo.setName("User Userovich");

        Item item1 = new Item();
        item1.setPrice(10000000);
        item1.setName("шкаф");
        item1.setId(1L);

        Item item2 = new Item();
        item2.setPrice(5000000);
        item2.setName("стул");
        item2.setId(2L);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setItem(item1);
        orderItem1.setCount(1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItem(item2);
        orderItem2.setCount(1);

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);

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

            assertEquals(testOrder.toString(), unmarshalled.toString());
        } catch(JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}