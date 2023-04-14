package com.rocketmq.demo.service;

public interface IOrderService {
    String getOrderByOrderNo(String orderNo);

    void processOrder(String orderNo);

    void saveOrder(String orderNo);
}
