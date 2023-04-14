package com.rocketmq.demo.listener;

import com.rocketmq.demo.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(nameServer = "${demo.rocketmq.business2.name-server:}",
        topic = "${demo.rocketmq.business2.consumer.topic:}",
        consumerGroup = "${demo.rocketmq.business2.consumer.group:}",
        selectorType = SelectorType.TAG,
        messageModel = MessageModel.CLUSTERING,
        enableMsgTrace = true,
        maxReconsumeTimes = 3
)
// 方式一：@RocketMQMessageListener注解 & 实现RocketMQListener接口
public class Business2Listener implements RocketMQListener<MessageExt> {

    private final IOrderService orderService;

    public Business2Listener(IOrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public void onMessage(MessageExt message) {
        log.debug("msgId：" + message.getMsgId());
        log.debug("keys：" + message.getKeys());
        log.debug("tags：" + message.getTags());
    }
}
