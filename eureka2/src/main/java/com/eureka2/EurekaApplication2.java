package com.eureka2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// http://127.0.0.1:8002
@SpringBootApplication
@EnableEurekaServer // 声明这个应用是一个EurekaServer (必须)
public class EurekaApplication2 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication2.class, args);
    }

}
