package ru.mart.andersen.creditor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.service.CreditOfferService;
import ru.mart.andersen.creditor.service.OrderCreditService;
import ru.mart.andersen.creditor.service.OrderService;
import ru.mart.andersen.creditor.to.OrderTo;
import ru.mart.andersen.creditor.util.exceptions.ApplicationValidationException;
import ru.mart.andersen.creditor.util.exceptions.NoSuchOrderException;
import ru.mart.andersen.creditor.util.exceptions.OrderValidationException;
import ru.mart.andersen.creditor.util.exceptions.RecordNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    private final OrderCreditService orderCreditService;

    public OrderRestController(OrderCreditService orderCreditService) {
        this.orderCreditService = orderCreditService;
    }

    @ExceptionHandler({ApplicationValidationException.class})
    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(HttpServletResponse response, @RequestBody OrderTo orderTo) {
        UUID orderUid= orderCreditService.save(orderTo);
//            response.sendRedirect("/api/order?uid=" + orderUid);
        return new ResponseEntity<>("/api/order?uid=" + orderUid, HttpStatus.CREATED);
//        catch (IOException ex) {
//            //no op
//        }
    }

    @ExceptionHandler({ApplicationValidationException.class, RecordNotFoundException.class})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderTo> getOrder(@RequestParam(name = "uid") String uid) {
        OrderTo orderTo = orderCreditService.get(UUID.fromString(uid));
        return new ResponseEntity<>(orderTo, HttpStatus.OK);
    }
}
