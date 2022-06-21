package com.consumer.client.impl;

import com.common.model.User;
import com.consumer.client.ProducerFeign;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerFeignImplHystrix implements ProducerFeign {

    @Override
    public List <User> getUser() {
        System.out.println("触发熔断，发送邮件给管理员！");
        //返回一个空的数组,防止调用方报错,不至于影响到其他功能的使用
        return new ArrayList <>();
    }
}