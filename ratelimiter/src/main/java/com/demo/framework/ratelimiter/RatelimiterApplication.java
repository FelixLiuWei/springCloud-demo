package com.demo.framework.ratelimiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RatelimiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(RatelimiterApplication.class, args);
    }
}
