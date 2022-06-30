/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.framework.mq.autoconfigure;

import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 加载应用属性配置文件的处理类
 */

public class DemoRocketMQProperties {

   @ConfigurationProperties(prefix = "demo.rocketmq.business1")
   public class DemoBusiness1RocketMQProperties extends RocketMQProperties {
   }

   @ConfigurationProperties(prefix = "demo.rocketmq.business2")
   public class DemoBusiness2RocketMQProperties extends RocketMQProperties {
   }

   @ConfigurationProperties(prefix = "demo.rocketmq.business3")
   public class DemoBusiness3RocketMQProperties extends RocketMQProperties {
   }

   @ConfigurationProperties(prefix = "demo.rocketmq.business4")
   public class DemoBusiness4RocketMQProperties extends RocketMQProperties {
   }
}