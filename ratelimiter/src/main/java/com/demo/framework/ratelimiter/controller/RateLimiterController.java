package com.demo.framework.ratelimiter.controller;

import com.demo.framework.ratelimiter.annotation.Limit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 限流测试
 *
 * @Author felix
 */
@RestController
@RequestMapping("/ratelimiter")
public class RateLimiterController {

    /**
     * 开启限流，限制请求1个
     * @return
     */
    @GetMapping("/request1")
    @Limit(limitNum = 500, name = "request1")
    public String openRateLimiter1() {
        System.out.println("【限流1执行了....编写业务....】");
        return "限流1执行了";
    }

    /**
     * 开启限流，限制请求2个
     * @return
     */
    @GetMapping("/request2")
    @Limit(limitNum = 700, name = "request2")
    public String openRateLimiter2() {
        System.out.println("【限流2执行了....编写业务....】");
        return "限流2执行了";
    }

    /**
     * 不限流
     * @return
     */
    @GetMapping("/request3")
    public String openRateLimiter3() {
        System.out.println("【不限流3执行了】");
        return "不限流3执行了";
    }
}
