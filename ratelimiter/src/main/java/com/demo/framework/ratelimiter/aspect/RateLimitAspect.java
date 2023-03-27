package com.demo.framework.ratelimiter.aspect;

import com.demo.framework.ratelimiter.annotation.Limit;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 切面处理
 *
 * @Author felix
 */
@Aspect
@Component
@Slf4j
public class RateLimitAspect {

    private ConcurrentHashMap <String, RateLimiter> RATE_LIMITER = new ConcurrentHashMap <>();

    private RateLimiter rateLimiter;

    @Pointcut("@annotation(com.demo.framework.ratelimiter.annotation.Limit)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object object = null;

        //获取拦截的方法名
        Signature signature = joinPoint.getSignature();

        //获取拦截的方法名
        MethodSignature methodSignature = (MethodSignature) signature;

        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();

        //获取注解信息
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        //获取注解信息
        Limit annotation = currentMethod.getAnnotation(Limit.class);

        //获取注解每秒加入桶中的token
        double limitNum = annotation.limitNum();

        // 注解所在方法名区分不同的限流策略
        String functionName = methodSignature.getName();

        if (RATE_LIMITER.containsKey(functionName)) {
            rateLimiter = RATE_LIMITER.get(functionName);
        } else {
            // 创建方式一：SmoothBursty
            // 适合场景：允许某一时间突发流量的情况
            // 具体规则：根据指定的稳定吞吐率创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询）。
            // 1. 返回的RateLimiter 确保了在平均情况下，每秒发布的许可数不会超过permitsPerSecond，每秒钟会持续发送请求。
            // 2. 当传入请求速率超过permitsPerSecond，速率限制器会每秒释放一个许可(1.0 / permitsPerSecond 这里是指设定了permitsPerSecond为1.0) 。
            // 3. 当速率限制器闲置时，允许许可数暴增到permitsPerSecond，随后的请求会被平滑地限制在稳定速率permitsPerSecond中。
            RATE_LIMITER.putIfAbsent(functionName, RateLimiter.create(limitNum));


            // 创建方式一：SmoothWarmingUp
            // 适合场景：适合要求请求速率缓慢提升的情况
            // 具体规则：根据指定的稳定吞吐率和预热期来创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询），
            // 在这段预热时间内，RateLimiter每秒分配的许可数会平稳地增长直到预热期结束时达到其最大速率（只要存在足够请求数来使其饱和）。
            // 同样地，如果RateLimiter 在warmupPeriod时间内闲置不用，它将会逐步地返回冷却状态。
            // 也就是说，它会像它第一次被创建般经历同样的预热期。
            // 返回的RateLimiter 主要用于那些需要预热期的资源，这些资源实际上满足了请求（比如一个远程服务），而不是在稳定（最大）的速率下可以立即被访问的资源。
            // 返回的RateLimiter 在冷却状态下启动（即预热期将会紧跟着发生），并且如果被长期闲置不用，它将回到冷却状态。
            // RATE_LIMITER.putIfAbsent(functionName, RateLimiter.create(limitNum, 10, TimeUnit.SECONDS));
            rateLimiter = RATE_LIMITER.get(functionName);
        }

        if (rateLimiter.tryAcquire()) {
            log.info("执行成功！！！开始执行业务处理");
            return joinPoint.proceed();
        } else {
            log.info("请求繁忙...做一些系统处理");
            return null;
        }
    }

}
