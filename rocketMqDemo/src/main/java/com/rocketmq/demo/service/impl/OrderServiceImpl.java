package com.rocketmq.demo.service.impl;

import com.rocketmq.demo.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Override
    @Cacheable(key = "#orderNo")
    public String getOrderByOrderNo(String orderNo) {
        return "orderNo_" + orderNo;
    }

    @Override
    public void processOrder(String orderNo) {
        log.debug("订单处理逻辑, 处理订单:" + orderNo);
    }

    @Override
    public void saveOrder(String orderNo) {
        log.debug("订单保存逻辑, 创建订单:" + orderNo);
    }
}
