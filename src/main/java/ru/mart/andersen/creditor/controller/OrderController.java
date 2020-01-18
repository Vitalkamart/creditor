package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.service.CreditOfferService;
import ru.mart.andersen.creditor.service.OrderService;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.util.exceptions.NoSuchOrderException;
import ru.mart.andersen.creditor.util.exceptions.OrderValidationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;
    private CreditOfferService creditOfferService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setCreditOfferService(CreditOfferService creditOfferService) {
        this.creditOfferService = creditOfferService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(HttpServletResponse response, @RequestBody OrderTo orderTo) {
        try {
            System.out.println("OrderController /api/order/ with orderTo=" + orderTo);
            UUID orderUid= orderService.save(orderTo);
            System.out.println("OrderController order saved with uid= " + orderUid);
//            response.sendRedirect("/login?orderUid=" + orderUid);
            return new ResponseEntity<>("/login?orderUid=" + orderUid, HttpStatus.CREATED);
        } catch (OrderValidationException ex) {
            return  new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
//        catch (IOException ex) {
//            //no op
//        }

    }

    @GetMapping(value = "/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderTo> getOrder(@PathVariable(name = "uid") String uid) {
        try {
            OrderTo orderTo = orderService.get(UUID.fromString(uid));
            return new ResponseEntity<>(orderTo, HttpStatus.OK);
        } catch (NoSuchOrderException ex) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
}
