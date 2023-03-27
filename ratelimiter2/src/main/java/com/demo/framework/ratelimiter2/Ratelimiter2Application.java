package com.demo.framework.ratelimiter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Ratelimiter2Application {
    public static void main(String[] args) {
        SpringApplication.run(Ratelimiter2Application.class, args);
    }
}
