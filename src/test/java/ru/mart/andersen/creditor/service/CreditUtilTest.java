package ru.mart.andersen.creditor.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.model.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.service.CreditUtil.*;

class CreditUtilTest {
    private static CreditOffer creditOffer;
    private static BigDecimal price;
    private static BigDecimal amount;

    @BeforeAll
    static void init() {
        price = BigDecimal.valueOf(100000);
        amount = BigDecimal.valueOf(90000);

        creditOffer = new CreditOffer();
        Order order = new Order();
        order.setDiscount(100);                   // 10%
        order.setPrice(price.intValue());         // 100 000,00
        creditOffer.setPeriod(12);                // 12 months
        creditOffer.setAmount(amount.intValue()); // 90 000,00 (cause of 10% discount)
        creditOffer.setOrder(order);
        creditOffer.setCreditRate(199);           // 19,9%
    }

    @Test
    @DisplayName("calculate amount with discount")
    void getCreditOfferAmount() {
        int priceInt = 100_000;
        int discountInt = 17;
        BigDecimal result = getOfferAmount(priceInt, discountInt);

        assertEquals(83_000, result.intValue());
        assertThrows(IllegalArgumentException.class, () -> getOfferAmount(-5, 17));
        assertThrows(IllegalArgumentException.class, () -> getOfferAmount(100000, 120));
        assertThrows(IllegalArgumentException.class, () -> getOfferAmount(100000, -5));
    }

    @Test
    void singlePayTest() {
        BigDecimal expected = new BigDecimal("8332.80").setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal calculated = calculateSinglePayment(creditOffer.getCreditRate(), creditOffer.getPeriod(), amount)
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        assertEquals(expected, calculated);
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(30, 120, amount));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(250, 120, amount));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(200, 0, amount));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(200, 140, amount));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(200, 120, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(200, 120, BigDecimal.valueOf(-1)));
    }

    @Test
    void findBestInterestTest() {
        List<Integer> rates = new ArrayList<>();
        for (int i = 50; i < 240; i++) {  // from 5% to 24%
            rates.add(i);
        }
        int calculated = findBestInterest(rates, creditOffer.getPeriod(), price, amount);
        int expected = 199;
        assertEquals(expected, calculated);
    }


}