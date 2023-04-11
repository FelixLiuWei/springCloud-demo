package com.annotation.demo.listener;

import com.annotation.demo.model.RegisterEvent;
import com.annotation.demo.model.SucceedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RegisterEventListener {

    @Order(2)
    @EventListener
    public void handleNotifyEvent1(RegisterEvent registerEvent) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": 开始处理1事件: " + registerEvent.getUsername());
        TimeUnit.SECONDS.sleep(5);
    }

    @Order(1)
    @EventListener(condition = "#registerEvent.username.equals(\"Felix2\")")
    public void handleNotifyEvent2(RegisterEvent registerEvent) {
        System.out.println(Thread.currentThread().getName() + ": 开始处理2事件: " + registerEvent.getUsername());
    }

    @EventListener(classes = SucceedEvent.class)
    public void handleNotifyEvent3(SucceedEvent succeedEvent) {
        System.out.println(Thread.currentThread().getName() + ": 开始处理3事件: " + succeedEvent.getUsername());
    }
}
