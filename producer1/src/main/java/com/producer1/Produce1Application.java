package com.producer1;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class Produce1Application {
    public static void main(String[] args) {
        SpringApplication.run(Produce1Application.class, args);
    }
}
