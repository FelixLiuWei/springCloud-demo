package com.demo.framework.ratelimiter.annotation;

import java.lang.annotation.*;

/**
 * RateLimiter限流注解
 *
 * @author felix
 *
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    double limitNum() default 20;

    String name() default "";
}
