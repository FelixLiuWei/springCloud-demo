package com.annotation.demo.controller;

import com.alibaba.fastjson.JSON;
import com.annotation.demo.service.IUserService;
import com.common.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public void createUser() {
        User user = userService.insertTestNoRollbackFor();
        System.out.println(JSON.toJSON(user).toString());
    }

    @GetMapping("/get/{userId}")
    public void getUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
    }
}
