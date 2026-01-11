package com.gateway.bankgateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("account-fallback")
    public String accountFallBack() {
        return "Account service is down.. please try again later...";
    }
    @GetMapping("cards-fallback")
    public String cardsFallBack() {
        return "cards service is down.. please try again later...";
    }
    @GetMapping("loans-fallback")
    public String loansFallBack() {
        return "loans service is down.. please try again later...";
    }
}
