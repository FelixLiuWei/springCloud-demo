#RocketMQ（客户端）的 starter 实现过程。
##1. spring-boot-starter 的实现步骤： 
###1.1 pom文件定义
####    a. 定义最终要生成的 starter 组件信息
```
<groupId>com.felix.springcloud</groupId>
<artifactId>springCloud-demo-mq-starter</artifactId>
<version>1.0</version>
``` 
####    b. 定义依赖包
```xml
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-spring-boot-starter</artifactId>
    <version>${RELEASE.VERSION}</version>
</dependency>
``` 
###1.2 定义自动加载类
####    resources/META-INF/spring.factories
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.demo.framework.mq.autoconfigure.CloudDemoRocketMQAutoConfiguration
``` 
####    a. 自定义实现：RocketMQProperties 加载应用属性配置文件的处理类 - CloudDemoRocketMQAutoConfiguration

####    b. 自定义实现：RocketMQTemplate 发送端用户发送消息的发送模板类 - CloudDemoRocketMQProperties

###1.3 RocketMq发送端封装
在发送端（producer）和消费端（consumer）客户端分别进行封装，在当前的实现版本提供了对 Spring Messaging 接口的兼容方式。

####    a. 普通发送端

####    b. 事务消息发送端

###1.4 RocketMq消费端封装