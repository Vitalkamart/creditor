package ru.mart.andersen.creditor.service;

import org.springframework.stereotype.Service;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.mart.andersen.creditor.util.CreditUtil.*;
import static ru.mart.andersen.creditor.util.ValidationUtil.*;
import static ru.mart.andersen.creditor.util.validators.CreditOfferValidator.validateCreditOffer;

@Service
public class PaymentService {
    public List<Payment> getPaymentList(CreditOffer creditOffer) {
        validateCreditOffer(creditOffer);

        BigDecimal remaining = creditOffer.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN);
        int period = creditOffer.getPeriod();
        int creditRate = creditOffer.getCreditRate();
        BigDecimal monthPay = calculateSinglePayment(creditRate, period,remaining)
                .setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal interest = getInterestFromCreditRateAndPeriod(creditRate, period);

        List<Payment> paymentList = new ArrayList<>();

        for (int month = 1; month <= period; month++) {
            Payment payment = new Payment();
            BigDecimal interestPay = remaining.multiply(interest).setScale(2, BigDecimal.ROUND_HALF_DOWN);
            BigDecimal principalPay = monthPay.subtract(interestPay).setScale(2, BigDecimal.ROUND_HALF_DOWN);

            payment.setMonth(month);
            payment.setPayment(monthPay);
            payment.setInterestPayment(interestPay);
            payment.setPrincipalPayment(principalPay);
            payment.setRemainingBalance(remaining);
            paymentList.add(payment);

            remaining = remaining.subtract(principalPay);
        }
        return paymentList;
    }

}
