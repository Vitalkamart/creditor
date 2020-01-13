package ru.mart.andersen.creditor.service;

import org.springframework.stereotype.Service;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ru.mart.andersen.creditor.service.CreditUtil.findBestInterest;

@Service
public class PaymentService {
    public List<Payment> getPaymentList(CreditOffer creditOffer) {

        BigDecimal price = BigDecimal.valueOf(creditOffer.getOrder().getPrice());
        BigDecimal amount = BigDecimal.valueOf(creditOffer.getAmount());
        int period = creditOffer.getPeriod();

        List<Payment> paymentList = new ArrayList<>();

        for (int month = 0; month <= period; ++month) {
            Payment payment = new Payment();

            paymentList.add(payment);
        }
        return null;
    }
}
