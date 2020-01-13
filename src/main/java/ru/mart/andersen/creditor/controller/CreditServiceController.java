package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.service.CreditService;
import ru.mart.andersen.creditor.service.OrderService;
import ru.mart.andersen.creditor.service.UserService;
import ru.mart.andersen.creditor.to.OrderTo;

@Controller
public class CreditServiceController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private CreditService creditService;

    @PostMapping (value = "/order", consumes = MediaType.APPLICATION_XML_VALUE)
    public String postOrder(@RequestBody OrderTo orderTo) {
        creditService.manageOrder(orderTo);
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

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name = "user") User user) {
        userService.save(user);
        return "home";
    }
}
