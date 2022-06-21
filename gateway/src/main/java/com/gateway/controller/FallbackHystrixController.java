package com.gateway.controller;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;

@Slf4j
@RestController
public class FallbackHystrixController {
    @GetMapping("/producerFallbackHystrix")
    public ResponseEntity producerFallbackHystrix(ServerWebExchange exchange) {
        System.out.println("=============通过网关层调用producer 触发熔断处理，发送邮件给管理员,进行处理=============");
        log.warn("服务降级...{}", Objects.toString(exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR)));

        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);

        if (exception instanceof HystrixTimeoutException) {
            return ResponseEntity.ok("网关接口请求超时...");
        }

        return ResponseEntity.ok("服务暂时不可用，请稍后重试...");
    }

    @GetMapping("/consumerFallbackHystrix")
    public ResponseEntity consumerFallbackHystrix(ServerWebExchange exchange) {
        System.out.println("=============通过网关层调用consumer 触发熔断处理，发送邮件给管理员,进行处理=============");

        log.warn("服务降级...{}", Objects.toString(exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR)));

        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);

        if (exception instanceof HystrixTimeoutException) {
            return ResponseEntity.ok("网关接口请求超时...");
        }

        return ResponseEntity.ok("服务暂时不可用，请稍后重试...");
    }
}
