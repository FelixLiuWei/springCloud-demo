package com.rocketmq.demo.listener;

import com.rocketmq.demo.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
// 方式二：配置文件定义consumer（ConsumerConfig.java） 并实现MessageListenerOrderly| MessageListenerConcurrently 接口
public class Business1Listener implements MessageListenerOrderly {

    private final IOrderService orderService;

    public Business1Listener(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        for (MessageExt message : msgs) {
            try {
                log.debug("开始消费！message:" + message.getKeys());
                String orderNo = message.getKeys();
                // 判断订单是否已经处理过
                if (isOrderProcessed(orderNo)) {
                    // 如果订单已经处理过，则直接返回成功
                    return ConsumeOrderlyStatus.SUCCESS;
                }
                // 处理订单
                orderService.processOrder(orderNo);
                // 将订单号持久化到数据库中
                orderService.saveOrder(orderNo);
            } catch (Exception e) {
                // 处理消息失败，记录消息 ID
                String messageId = message.getMsgId();
                recordFailedMessage(messageId);
                // 返回 RECONSUME_LATER，让消息重新消费
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }

    public Boolean isOrderProcessed(String orderNo) {
        // 从数据库中查询订单号是否已经处理过
        return StringUtils.isNotBlank(orderService.getOrderByOrderNo(orderNo));
    }

    private void recordFailedMessage(String messageId) {
        // 记录消息 ID，例如将其写入数据库
    }

}
