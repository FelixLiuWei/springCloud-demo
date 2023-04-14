package com.demo.framework.mq.autoconfigure;

import com.demo.framework.mq.constant.DemoRocketMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.MQAdmin;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.*;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 发送端用户发送消息的发送模板类
 */

@Slf4j
@Configuration
@EnableConfigurationProperties({
        DemoRocketMQProperties.DemoBusiness1RocketMQProperties.class,
        DemoRocketMQProperties.DemoBusiness2RocketMQProperties.class,
        DemoRocketMQProperties.DemoBusiness3RocketMQProperties.class,
        DemoRocketMQProperties.DemoBusiness4RocketMQProperties.class
})
@ConditionalOnClass({MQAdmin.class})
@ConditionalOnProperty(
        prefix = "demo.rocketmq",
        value = "enable",
        havingValue = "true",
        matchIfMissing = true)
@Import({MessageConverterConfiguration.class, ListenerContainerConfiguration.class, ExtProducerResetConfiguration.class, ExtConsumerResetConfiguration.class, RocketMQTransactionConfiguration.class})
@AutoConfigureAfter({MessageConverterConfiguration.class})
@AutoConfigureBefore({RocketMQTransactionConfiguration.class})
public class DemoRocketMQAutoConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean(name = DemoRocketMQConstants.BUSINESS1_TEMPLATE, destroyMethod = "destroy")
    @ConditionalOnMissingBean(name = DemoRocketMQConstants.BUSINESS1_TEMPLATE)
    @ConditionalOnProperty(prefix = "demo.rocketmq.business1", value = {"name-server", "producer.group"})
    public RocketMQTemplate demoBusiness1RocketMQTemplate(DemoRocketMQProperties.DemoBusiness1RocketMQProperties rocketMQProperties, RocketMQMessageConverter rocketMQMessageConverter) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer(rocketMQProperties));
        rocketMQTemplate.setMessageConverter(rocketMQMessageConverter.getMessageConverter());
        return rocketMQTemplate;
    }

    @Bean(name = DemoRocketMQConstants.BUSINESS2_TEMPLATE, destroyMethod = "destroy")
    @ConditionalOnMissingBean(name = DemoRocketMQConstants.BUSINESS2_TEMPLATE)
    @ConditionalOnProperty(prefix = "demo.rocketmq.business2", value = {"name-server", "producer.group"})
    public RocketMQTemplate demoBusiness2RocketMQTemplate(DemoRocketMQProperties.DemoBusiness2RocketMQProperties rocketMQProperties, RocketMQMessageConverter rocketMQMessageConverter) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer(rocketMQProperties));
        rocketMQTemplate.setMessageConverter(rocketMQMessageConverter.getMessageConverter());
        return rocketMQTemplate;
    }

    @Bean(name = DemoRocketMQConstants.BUSINESS3_TEMPLATE, destroyMethod = "destroy")
    @ConditionalOnMissingBean(name = DemoRocketMQConstants.BUSINESS3_TEMPLATE)
    @ConditionalOnProperty(prefix = "demo.rocketmq.business3", value = {"name-server", "producer.group"})
    public RocketMQTemplate demoBusiness3RocketMQTemplate(DemoRocketMQProperties.DemoBusiness3RocketMQProperties rocketMQProperties, RocketMQMessageConverter rocketMQMessageConverter) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer(rocketMQProperties));
        rocketMQTemplate.setMessageConverter(rocketMQMessageConverter.getMessageConverter());
        return rocketMQTemplate;
    }

    @Bean(name = DemoRocketMQConstants.BUSINESS4_TEMPLATE, destroyMethod = "destroy")
    @ConditionalOnMissingBean(name = DemoRocketMQConstants.BUSINESS4_TEMPLATE)
    @ConditionalOnProperty(prefix = "demo.rocketmq.business4", value = {"name-server", "producer.group"})
    public RocketMQTemplate demoBusiness4RocketMQTemplate(DemoRocketMQProperties.DemoBusiness4RocketMQProperties rocketMQProperties, RocketMQMessageConverter rocketMQMessageConverter) {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(defaultMQProducer(rocketMQProperties));
        rocketMQTemplate.setMessageConverter(rocketMQMessageConverter.getMessageConverter());
        return rocketMQTemplate;
    }

    public DefaultMQProducer defaultMQProducer(DemoRocketMQProperties.DefaultRocketMQProperties rocketMQProperties) {
        RocketMQProperties.Producer producerConfig = rocketMQProperties.getProducer();
        String nameServer = rocketMQProperties.getNameServer();
        String groupName = producerConfig.getGroup();
        Assert.hasText(nameServer, "[rocketmq.name-server] must not be null");
        Assert.hasText(groupName, "[rocketmq.producer.group] must not be null");

        String accessChannel = rocketMQProperties.getAccessChannel();

        String ak = rocketMQProperties.getProducer().getAccessKey();
        String sk = rocketMQProperties.getProducer().getSecretKey();
        boolean isEnableMsgTrace = rocketMQProperties.getProducer().isEnableMsgTrace();
        String customizedTraceTopic = rocketMQProperties.getProducer().getCustomizedTraceTopic();

        DefaultMQProducer producer = RocketMQUtil.createDefaultMQProducer(groupName, ak, sk, isEnableMsgTrace, customizedTraceTopic);

        producer.setNamesrvAddr(nameServer);
        if (!StringUtils.isEmpty(accessChannel)) {
            producer.setAccessChannel(AccessChannel.valueOf(accessChannel));
        }
        producer.setSendMsgTimeout(producerConfig.getSendMessageTimeout());
        producer.setRetryTimesWhenSendFailed(producerConfig.getRetryTimesWhenSendFailed());
        producer.setRetryTimesWhenSendAsyncFailed(producerConfig.getRetryTimesWhenSendAsyncFailed());
        producer.setMaxMessageSize(producerConfig.getMaxMessageSize());
        producer.setCompressMsgBodyOverHowmuch(producerConfig.getCompressMessageBodyThreshold());
        producer.setRetryAnotherBrokerWhenNotStoreOK(producerConfig.isRetryNextServer());
        producer.setUseTLS(producerConfig.isTlsEnable());
        producer.setNamespace(producerConfig.getNamespace());
        log.info(String.format("a producer (%s) init on namesrv %s", groupName, nameServer));
        return producer;
    }

}
