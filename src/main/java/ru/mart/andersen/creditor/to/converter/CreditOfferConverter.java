package ru.mart.andersen.creditor.to.converter;

import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.to.CreditOfferTo;

public class CreditOfferConverter {
    public static CreditOfferTo getToFromCreditOffer(CreditOffer c) {
        CreditOfferTo cTo = new CreditOfferTo();

        cTo.setId(c.getId());
        cTo.setUid(c.getUid());
        cTo.setOrderUid(c.getOrder().getUid());
        cTo.setAmount(c.getAmount());
        cTo.setCreditRate(c.getCreditRate());
        cTo.setDateTime(c.getDateTime());
        cTo.setPeriod(c.getPeriod());
        cTo.setUserName(c.getUserName());

        return cTo;
    }
}
