package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.service.UserService;
import ru.mart.andersen.creditor.to.UserTo;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public String register(@ModelAttribute(name = "user") UserTo userTo, Model model) {
//        if (!userService.save(userTo)) {
//            model.addAttribute("error", "login " + userTo.getLogin() + " is already used");
//            return "register";
//        };
//        return "redirect:/home";
//    }
}
