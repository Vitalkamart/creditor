package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.service.CreditOfferService;
import ru.mart.andersen.creditor.to.CreditOfferTo;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.util.exceptions.ApplicationValidationException;

import java.util.UUID;

@RestController
@RequestMapping("/api/credit-offer")
public class CreditOfferRestController {
    private CreditOfferService creditOfferService;

    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @GetMapping
    @ExceptionHandler(ApplicationValidationException.class)
    public ResponseEntity<CreditOfferTo> getCreditOfferByOrderUid(
            @RequestParam("orderUid") String orderUid) {
        CreditOfferTo creditOfferTo = creditOfferService.getCreditOfferByOrderUid(orderUid);
        return new ResponseEntity<>(creditOfferTo, HttpStatus.OK);
    }
}
