package com.revpay.controller.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/me")
    public String me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Logged in as: " + auth.getName();
    }
}

