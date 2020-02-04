package ru.mart.andersen.creditor.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Order;
import ru.mart.andersen.creditor.util.exceptions.NoSuitableInterestException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mart.andersen.creditor.util.CreditUtil.*;

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
        order.setDiscount(10);                    // 10%
        order.setPrice(price);                    // 100 000,00
        creditOffer.setPeriod(12);                // 12 months
        creditOffer.setAmount(amount);            // 90 000,00 (cause of 10% discount)
        creditOffer.setOrder(order);
        creditOffer.setCreditRate(199);           // 19,9%
    }

    @Test
    @DisplayName("calculate amount with discount")
    void getCreditOfferAmount() {
        int discountInt = 10;
        BigDecimal result = getOfferAmount(price, discountInt);

        assertEquals(90000, result.intValue());
        assertThrows(NullPointerException.class,
                () -> getOfferAmount(null, 50));
        assertThrows(IllegalArgumentException.class,
                () -> getOfferAmount(BigDecimal.valueOf(-5), 50));
        assertThrows(IllegalArgumentException.class,
                () -> getOfferAmount(BigDecimal.valueOf(100000), 120));
        assertThrows(IllegalArgumentException.class,
                () -> getOfferAmount(BigDecimal.valueOf(100000), -5));
    }

    @Test
    @DisplayName("single pay test")
    void singlePayTest() {
        BigDecimal expected = new BigDecimal("8332.80")
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal calculated = calculateSinglePayment(creditOffer.getCreditRate(),
                creditOffer.getPeriod(), amount)
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
        assertThrows(NullPointerException.class,
                () -> calculateSinglePayment(200, 120, null));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(200, 120, BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class,
                () -> calculateSinglePayment(200, 120, BigDecimal.valueOf(-1)));
    }

    @Test
    @DisplayName("best interest odd massive")
    void findBestInterestTestOddMassive() {
        List<Integer> rates = new ArrayList<>();
        for (int i = 50; i <= 240; i++) {  // from 5% to 24%
            rates.add(i);
        }
        int calculated = findBestInterest(rates, creditOffer.getPeriod(), price, amount);
        int expected = 199;
        assertEquals(expected, calculated);
    }

    @Test
    @DisplayName("best interest even massive")
    void findBestInterestTestEvenMassive() {
        List<Integer> rates = new ArrayList<>();
        for (int i = 50; i <= 239; i++) {  // from 5% to 23,9%
            rates.add(i);
        }
        int calculated = findBestInterest(rates, creditOffer.getPeriod(), price, amount);
        int expected = 199;
        assertEquals(expected, calculated);
    }

    @Test
    @DisplayName("no suitable interest")
    void findBestInterestTestTooNarrowCreditInterestInterval() {
        List<Integer> rates = new ArrayList<>();
        for (int i = 200; i <= 239; i++) {  // from 20% to 24% (closest suitable is 19.9%)
            rates.add(i);
        }

        assertThrows(NoSuitableInterestException.class,
                () -> findBestInterest(rates, creditOffer.getPeriod(), price, amount));
    }

    @Test
    @DisplayName("best interest illegal credit rates")
    void findBestInterestWithIllegalCreditRateTest() {
        List<Integer> rates = new ArrayList<>();
        for (int i = 50; i < 240; i++) {
            if (i == 199) {
                rates.add(-6);
            } else {
                rates.add(i);
            }
        }

        assertThrows(IllegalArgumentException.class,
                () -> findBestInterest(rates, creditOffer.getPeriod(), price, amount));
    }



//    @Test
//    void getUUID() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(UUID.randomUUID());
//        }
//    }
}