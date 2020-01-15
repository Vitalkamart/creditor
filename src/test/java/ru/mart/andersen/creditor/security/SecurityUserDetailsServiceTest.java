package ru.mart.andersen.creditor.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUserDetailsServiceTest {
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//    @Test
//    void user1Password() {
//        System.out.println(encoder.encode("password1"));
//    }
//
//    @Test
//    void user2Password() {
//        System.out.println(encoder.encode("password2"));
//    }

}