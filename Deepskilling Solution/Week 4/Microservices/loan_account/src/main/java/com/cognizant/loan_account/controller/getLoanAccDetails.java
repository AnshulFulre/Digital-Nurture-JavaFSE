package com.cognizant.loan_account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class getLoanAccDetails {

    @GetMapping("/loans/{number}")
    public String getDetails(@PathVariable String number){
        return "{ number: \"H00987987972342\", type: \"car\", loan: 400000, emi: 3258, tenure:\n" +
                "18 } ";
    }
}
