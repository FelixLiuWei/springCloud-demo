package com.annotation.demo.controller;

import com.annotation.demo.model.RegisterEvent;
import com.annotation.demo.model.SucceedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("/send1")
    public void sendEvent1() {
        applicationContext.publishEvent(new RegisterEvent("Felix"));
    }

    @GetMapping("/send2")
    public void sendEvent2() {
        applicationContext.publishEvent(new RegisterEvent("Felix2"));
    }

    @GetMapping("/send3")
    public void sendEvent3() {
        applicationContext.publishEvent(new SucceedEvent("Felix3"));
    }
}
