package com.producer2;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class Produce2Application {
    public static void main(String[] args) {
        SpringApplication.run(Produce2Application.class, args);
    }
}
