package com.annotation.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.concurrent.*;

@Configuration
public class AnnotationConfiguration {

    @Bean(name = "myPool")
    public Executor taskExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,
                8,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        return RedisCacheManager.builder(redisConnectionFactory).build();
    }
}
