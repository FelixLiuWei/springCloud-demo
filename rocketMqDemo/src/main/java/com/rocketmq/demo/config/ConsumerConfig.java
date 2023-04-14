package com.rocketmq.demo.config;

import com.demo.framework.mq.autoconfigure.DemoRocketMQProperties;
import com.rocketmq.demo.listener.Business1Listener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Bean(name = "business1MQConsumer", destroyMethod = "shutdown")
    public DefaultMQPushConsumer rocketMQListenerContainer(DemoRocketMQProperties.DemoBusiness1RocketMQProperties properties,
                                                           Business1Listener business1Listener) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getConsumer().getGroup(), false, null);
        consumer.setNamesrvAddr(properties.getNameServer());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumerGroup(properties.getConsumer().getGroup());
        consumer.subscribe(properties.getConsumer().getTopic(), "*");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely());
        consumer.setMaxReconsumeTimes(4);
        consumer.setSuspendCurrentQueueTimeMillis(1000);
        consumer.registerMessageListener(business1Listener);
        consumer.start();
        return consumer;
    }

}