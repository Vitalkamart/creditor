package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.mart.andersen.creditor.service.OrderService;
import ru.mart.andersen.creditor.service.UserService;
import ru.mart.andersen.creditor.to.OrderTo;

@Controller
public class CreditServiceController {

    @Autowired
    private OrderService orderService;

    @PostMapping (value = "/order", consumes = MediaType.APPLICATION_XML_VALUE)
    public String postOrder(@RequestBody OrderTo orderTo) {
        long orderId = orderTo.getId();
        return "login?order_id=" + orderId;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }
}
