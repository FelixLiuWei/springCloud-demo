package com.producer.controller;

import com.common.model.User;
import com.producer.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity <List <User>> getAll() {
        System.out.println("我是服务器：producer!");
        return ResponseEntity.ok(userService.getAll());
    }

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/msg")
    public ResponseEntity <String> getMsg() {

        List <ServiceInstance> instances = discoveryClient.getInstances("consumer");

        ServiceInstance instance = instances.get(0);

        String url = instance.getUri() + "/user/getMsg";

        String msg = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(msg);
    }

    @GetMapping("/msg2")
    public ResponseEntity <String> getMsg2() {
        return ResponseEntity.ok("test2");
    }

    @Autowired
    private Environment env;

    @GetMapping("/config/{key}")
    public String apollo(@PathVariable String key) {
        System.out.println("我是服务器：producer!  apolloConfig.apolloKey=======" + env.getProperty(key));
        return "我是服务器：producer!" + env.getProperty(key);
    }
}