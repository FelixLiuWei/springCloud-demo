package com.consumer.client;

import com.common.model.User;
import com.consumer.client.impl.ProducerFeignImplHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "producer", fallback = ProducerFeignImplHystrix.class)  // 即将调用的 生产者的服务名称, fallback：触发熔断降级后，逻辑处理类
public interface ProducerFeign {
    @GetMapping("/user/all")
    List <User> getUser();
}
