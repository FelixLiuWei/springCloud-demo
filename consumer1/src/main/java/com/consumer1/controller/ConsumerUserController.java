package com.consumer1.controller;

import com.common.model.User;
import com.consumer1.client.ProducerFeign;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/query")
public class ConsumerUserController {

    //例一：使用ribbon前
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private DiscoveryClient discoveryClient;

//    @GetMapping("/getUserAll")
//    public ResponseEntity <List <User>> getUserAll() {
//        // 通过在application.yml中设置的name 获取在Eureka中注册的实例
//        // 因为  user-service  可能不只一个 (防止宕机)   所以是集合
//        List <ServiceInstance> instances = discoveryClient.getInstances("producer");
//
//        // 我们 只用第一个就行了  如果第一个宕机了那么 第2个自然就变成第一个了
//        //如果不懂去看一下Eureka的心跳机制  上面有
//        ServiceInstance instance = instances.get(0);
//
//        // getUri 获取producer服务的url然后拼接成http请求以及要请求的内容
//        String url = instance.getUri() + "/user/all";
//
//        // 通过http请求客户端工具 将请求发送  获取请求 对应的数据
//        List <User> list = restTemplate.getForObject(url, List.class);
//        return ResponseEntity.ok(list);
//    }

    //====================================================================================================

    //例二：使用ribbon后
//    @GetMapping("/getUserAll")
//    @HystrixCommand(fallbackMethod = "getUserAllFallback")
//    public ResponseEntity <List <User>> getUserAll() {
//        String url = "http://producer/user/all";
//
//        List <User> list = restTemplate.getForObject(url, List.class);
//        return ResponseEntity.ok(list);
//    }

//    //访问失败后执行的方法
//    public ResponseEntity<List<User>> getUserAllFallback()  {
//        //发送邮件.....给管理员
//        System.out.println("发送邮件给Amdin!");
//
//        System.out.println("系统预警!");
//        //进行报警
//        return ResponseEntity.ok(new ArrayList<User>());
//    }

    //====================================================================================================

    @Resource
    private ProducerFeign producerFeign;

    //例三：使用openFeign后
    @GetMapping("/getUserAll")
    public ResponseEntity <List<User>> getUserAll() {
        return ResponseEntity.ok(producerFeign.getUser());
    }

    @GetMapping("/getMsg")
    public ResponseEntity <String> getMsg() {
        return ResponseEntity.ok("Test");
    }

}