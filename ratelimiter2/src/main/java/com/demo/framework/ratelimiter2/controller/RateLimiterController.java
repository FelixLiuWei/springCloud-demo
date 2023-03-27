package com.demo.framework.ratelimiter2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流测试
 *
 * @Author felix
 */
@RestController
@RequestMapping("/ratelimiter2")
public class RateLimiterController {

    @GetMapping("/request1")
    public String openRateLimiter1() {
        System.out.println("【限流1执行了....编写业务....】");
        return "限流1执行了";
    }

    @GetMapping("/request2")
    public String openRateLimiter2() {
        System.out.println("【限流2执行了....编写业务....】");
        return "限流2执行了";
    }

    @GetMapping("/request3")
    public String openRateLimiter3() {
        System.out.println("【限流3执行了....编写业务....】");
        return "限流3执行了";
    }
}
