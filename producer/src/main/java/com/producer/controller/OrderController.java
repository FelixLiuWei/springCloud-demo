package com.producer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    /**
     * 下单接口
     */
    @PostMapping("/place")
    public void placeOrder(){

    }

}
