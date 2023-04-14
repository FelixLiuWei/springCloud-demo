package com.rocketmq.demo.controller;

import com.demo.framework.mq.autoconfigure.DemoRocketMQProperties;
import com.demo.framework.mq.constant.DemoRocketMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final RocketMQTemplate rocketMQTemplate1;
    private final RocketMQTemplate rocketMQTemplate2;
    private final DemoRocketMQProperties.DemoBusiness1RocketMQProperties demoBusiness1RocketMQProperties;
    private final DemoRocketMQProperties.DemoBusiness2RocketMQProperties demoBusiness2RocketMQProperties;

    public OrderController(@Qualifier(DemoRocketMQConstants.BUSINESS1_TEMPLATE) RocketMQTemplate rocketMQTemplate1,
                           @Qualifier(DemoRocketMQConstants.BUSINESS2_TEMPLATE) RocketMQTemplate rocketMQTemplate2,
                           DemoRocketMQProperties.DemoBusiness1RocketMQProperties demoBusiness1RocketMQProperties,
                           DemoRocketMQProperties.DemoBusiness2RocketMQProperties demoBusiness2RocketMQProperties) {
        this.rocketMQTemplate1 = rocketMQTemplate1;
        this.rocketMQTemplate2 = rocketMQTemplate2;
        this.demoBusiness1RocketMQProperties = demoBusiness1RocketMQProperties;
        this.demoBusiness2RocketMQProperties = demoBusiness2RocketMQProperties;
    }

    /**
     * 顺序同步发送消息给Business2，按orderNo作为key区分，每个orderNo放入同一个queue
     */
    @PostMapping("/batchCreate")
    public String createOrderList(@RequestBody String msg) throws UnsupportedEncodingException {
        log.debug("send msg:" + msg);
        for (int i = 0; i < 10; i++) {
            String key = null;
            if (i % 4 == 0) {
                key = "orderNo0";
            } else if (i % 4 == 1) {
                key = "orderNo1";
            } else if (i % 4 == 2) {
                key = "orderNo2";
            } else if (i % 4 == 3) {
                key = "orderNo3";
            }
            String topic = demoBusiness1RocketMQProperties.getConsumer().getTopic();
            String tag = "increasePrice";
            Map<String, Object> headers = new HashMap<>();
            headers.put(RocketMQHeaders.PREFIX + RocketMQHeaders.KEYS, key);
            GenericMessage message = new GenericMessage(msg + i, headers);
            rocketMQTemplate1.syncSendOrderly(topic + ":" + tag, message, key);
        }
        return null;
    }

    /**
     * send msg to topic Business2
     */
    @PostMapping("/create")
    public void createOrder(@RequestBody String msg) {
        log.debug("send msg:" + msg);
        rocketMQTemplate2.convertAndSend(demoBusiness2RocketMQProperties.getConsumer().getTopic(), msg);
    }

}
