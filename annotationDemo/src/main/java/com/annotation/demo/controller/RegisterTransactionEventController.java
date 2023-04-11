package com.annotation.demo.controller;

import com.annotation.demo.model.RegisterTransactionEvent;
import com.annotation.demo.service.ITransactionEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterTransactionEventController {

    @Autowired
    private ITransactionEventService transactionEventService;

    @GetMapping("/createEvent1")
    public void sendEvent1() {
        transactionEventService.save(new RegisterTransactionEvent("tx1"));
    }

    @GetMapping("/createEvent2")
    public void sendEvent2() {
        transactionEventService.save(new RegisterTransactionEvent("tx2"));
    }

    @GetMapping("/createEvent3")
    public void sendEvent3() {
        transactionEventService.save(new RegisterTransactionEvent("tx3"));
    }
}
