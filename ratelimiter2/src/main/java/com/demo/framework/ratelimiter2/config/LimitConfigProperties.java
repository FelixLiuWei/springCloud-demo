package com.demo.framework.ratelimiter2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limit")
@Data
public class LimitConfigProperties {
    private double permitsPerSecond;

    private long warmupPeriod;

}
