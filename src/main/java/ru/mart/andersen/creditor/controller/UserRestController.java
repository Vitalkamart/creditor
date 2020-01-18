package ru.mart.andersen.creditor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mart.andersen.creditor.service.UserService;
import ru.mart.andersen.creditor.to.UserTo;
import ru.mart.andersen.creditor.util.exceptions.UserValidationException;

@RestController
@RequestMapping("/api/user/")
public class UserRestController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody UserTo userTo) {
        try {
            if (!userService.save(userTo)) {
                return new ResponseEntity<>("login is already used", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("user is registered", HttpStatus.CREATED);
            }
        } catch (UserValidationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
