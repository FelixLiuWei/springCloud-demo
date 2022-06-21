package com.producer1.controller;

import com.common.model.User;
import com.producer1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


        System.out.println("我是服务器：producer1!");
        return ResponseEntity.ok(userService.getAll());
    }

}