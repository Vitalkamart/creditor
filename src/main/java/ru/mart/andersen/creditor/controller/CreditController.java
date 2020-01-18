package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.service.CreditOfferService;
import ru.mart.andersen.creditor.to.OrderTo;

import java.util.UUID;

@Controller
public class CreditController {

    private CreditOfferService creditOfferService;

    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

}
