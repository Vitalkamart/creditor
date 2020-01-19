package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.model.CreditOffer;
import ru.mart.andersen.creditor.model.Payment;
import ru.mart.andersen.creditor.service.CreditOfferService;
import ru.mart.andersen.creditor.service.PaymentService;
import ru.mart.andersen.creditor.util.exceptions.ApplicationValidationException;
import ru.mart.andersen.creditor.util.exceptions.RecordNotFoundException;

import java.util.List;


@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private PaymentService paymentService;
    private CreditOfferService creditOfferService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @GetMapping("/")
    @ExceptionHandler({ApplicationValidationException.class, RecordNotFoundException.class})
    public ResponseEntity<List<Payment>> getPaymentList(@RequestParam("creditOfferId") long id) {
        CreditOffer creditOffer = creditOfferService.getCreditOfferById(id);
        List<Payment> paymentList = paymentService.getPaymentList(creditOffer);
        return new ResponseEntity<>(paymentList, HttpStatus.OK);
    }
}
