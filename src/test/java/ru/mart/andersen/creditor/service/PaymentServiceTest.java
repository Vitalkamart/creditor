package ru.mart.andersen.creditor.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.model.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private static CreditOffer creditOffer;
    private static PaymentService paymentService;
    private static List<Payment> expected;

    @BeforeAll
    static void init() {
        paymentService = new PaymentService();
        expected = new ArrayList<>();
        fillExpectedList();

        creditOffer = new CreditOffer();
        Order order = new Order();
        order.setDiscount(100);                                        // 10%
        order.setPrice(BigDecimal.valueOf(100000));                    // 100 000,00
        creditOffer.setPeriod(12);                                     // 12 months
        creditOffer.setAmount(BigDecimal.valueOf(90000));              // 90 000,00 (cause of 10% discount)
        creditOffer.setOrder(order);
        creditOffer.setCreditRate(199);                                // 19,9%

    }

    private static void fillExpectedList() {
        expected.add(new Payment(1, new BigDecimal("90000"), new BigDecimal("6840.30"),
                new BigDecimal("1492.50"), new BigDecimal("8332.80")));
        expected.add(new Payment(2, new BigDecimal("83159.70"), new BigDecimal("6953.73"),
                new BigDecimal("1379.07"), new BigDecimal("8332.80")));
        expected.add(new Payment(3, new BigDecimal("76205.97"), new BigDecimal("7069.05"),
                new BigDecimal("1263.75"), new BigDecimal("8332.80")));
        expected.add(new Payment(4, new BigDecimal("69136.92"), new BigDecimal("7186.28"),
                new BigDecimal("1146.52"), new BigDecimal("8332.80")));
        expected.add(new Payment(5, new BigDecimal("61950.64"), new BigDecimal("7305.45"),
                new BigDecimal("1027.35"), new BigDecimal("8332.80")));
        expected.add(new Payment(6, new BigDecimal("54645.19"), new BigDecimal("7426.60"),
                new BigDecimal("906.20"), new BigDecimal("8332.80")));
        expected.add(new Payment(7, new BigDecimal("47218.59"), new BigDecimal("7549.76"),
                new BigDecimal("783.04"), new BigDecimal("8332.80")));
        expected.add(new Payment(8, new BigDecimal("39668.83"), new BigDecimal("7674.96"),
                new BigDecimal("657.84"), new BigDecimal("8332.80")));
        expected.add(new Payment(9, new BigDecimal("31993.87"), new BigDecimal("7802.23"),
                new BigDecimal("530.57"), new BigDecimal("8332.80")));
        expected.add(new Payment(10, new BigDecimal("24191.64"), new BigDecimal("7931.62"),
                new BigDecimal("401.18"), new BigDecimal("8332.80")));
        expected.add(new Payment(11, new BigDecimal("16260.02"), new BigDecimal("8063.15"),
                new BigDecimal("269.65"), new BigDecimal("8332.80")));
        expected.add(new Payment(12, new BigDecimal("8196.87"), new BigDecimal("8196.87"),
                new BigDecimal("135.93"), new BigDecimal("8332.80")));
    }

    @Test
    void getPaymentListTest() {
        List<Payment> payments = paymentService.getPaymentList(creditOffer);

        assertNotEquals(expected, payments);
        assertEquals(expected.toString(), payments.toString());
    }

}