package com.producer2.controller;

import com.common.model.User;
import com.producer2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity <List <User>> getAll() {
        System.out.println("我是服务器：producer2!");
        return ResponseEntity.ok(userService.getAll());
    }

    @Autowired
    private Environment env;

    @GetMapping("/config/{key}")
    public String apollo(@PathVariable String key) {
        System.out.println("我是服务器：producer2!  apolloConfig.apolloKey=======" + env.getProperty(key));
        return "我是服务器：producer2!" + env.getProperty(key);
    }
}