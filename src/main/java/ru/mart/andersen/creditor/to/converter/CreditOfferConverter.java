package ru.mart.andersen.creditor.to.converter;

import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.to.CreditOfferTo;

import java.math.BigDecimal;

public class CreditOfferConverter {
    public static CreditOfferTo getToFromCreditOffer(CreditOffer c) {
        CreditOfferTo creditOfferTo = new CreditOfferTo();

        creditOfferTo.setId(c.getId());
        creditOfferTo.setUid(c.getUid());
        creditOfferTo.setOrderUid(c.getOrder().getUid());
        creditOfferTo.setAmount(c.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        creditOfferTo.setCreditRate(c.getCreditRate());
        creditOfferTo.setDateTime(c.getDateTime());
        creditOfferTo.setPeriod(c.getPeriod());
        creditOfferTo.setUserName(c.getUserName());

        return creditOfferTo;
    }
}
