package com.demo.framework.ratelimiter2.util;

import com.demo.framework.ratelimiter2.config.LimitConfigProperties;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RateLimitUtil {
    @Autowired
    private LimitConfigProperties limitConfigProperties;

    private RateLimiter limiter;

    @PostConstruct
    public RateLimiter init(){
        if (limiter == null) {
            // 控制每秒向令牌桶放多少个令牌
            limiter = RateLimiter.create(limitConfigProperties.getPermitsPerSecond());
        }
        return limiter;
    }

    public RateLimiter getLimiter(){
        return this.limiter;
    }

    public boolean tryAcquire() {
        return limiter.tryAcquire();
    }
}
