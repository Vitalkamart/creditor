package ru.mart.andersen.creditor.to;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mart.andersen.creditor.TestUtils.getTestOrderTo;

class OrderToTest {
    private static String xmlOrderToString;
    private static Marshaller marshaller;
    private static OrderTo testOrder;

    public static Unmarshaller unmarshaller;

    @BeforeAll
    static void init() {
        initXmlOrderToString();
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
        testOrder = getTestOrderTo();
    }

    @Test
    @DisplayName("xml marshalling/unmarshalling of OrderTo")
    void marshallingAndUnmarshalling() {
        try {
            StringWriter sw = new StringWriter();

            marshaller.marshal(testOrder, sw);

            XMLStreamReader sr = XMLInputFactory.newInstance()
                    .createXMLStreamReader(new StringReader(sw.toString()));

            JAXBElement<OrderTo> unmarshalledXML = unmarshaller.unmarshal(sr, OrderTo.class);

            OrderTo unmarshalled = unmarshalledXML.getValue();
            sw.close();
            sr.close();

            assertEquals(testOrder.toString(), unmarshalled.toString());
        } catch (XMLStreamException | JAXBException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void initXmlOrderToString() {
        xmlOrderToString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<order>\n" +
                "    <id>1</id>\n" +
                "    <order_uid>8d2b87d2-fdf8-444b-9ada-bb2f00a47a3c</order_uid>\n" +
                "    <total_price>150000.00</total_price>\n" +
                "    <cart>\n" +
                "        <user>\n" +
                "            <id>1</id>\n" +
                "            <name>User Userovich</name>\n" +
                "            <login>login1</login>\n" +
                "        </user>\n" +
                "        <item_list>\n" +
                "            <item>\n" +
                "                <id>1</id>\n" +
                "                <name>диван</name>\n" +
                "                <price>100000.00</price>\n" +
                "            </item>\n" +
                "            <item>\n" +
                "                <id>2</id>\n" +
                "                <name>стул</name>\n" +
                "                <price>50000.00</price>\n" +
                "            </item>\n" +
                "        </item_list>\n" +
                "    </cart>\n" +
                "    <discount>0</discount>\n" +
                "</order>";
    }

//    @Test
//    void itemUID() {
//        for (int i = 0; i < 5; i++) {
//            System.out.println(UUID.randomUUID());
//        }
//    }
}