package com.demo.framework.ratelimiter2.config;

import com.demo.framework.ratelimiter2.filter.RatelimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebApplicationConfig {

    @Bean
    public FilterRegistrationBean registerLimitFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ratelimitFilter());
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        // 值越小，Filter越靠前
        registration.setOrder(1);

        return registration;
    }

    /**
     * 创建一个bean
     *
     * @return
     */
    @Bean(name = "ratelimitFilter")
    public Filter ratelimitFilter() {
        return new RatelimitFilter();
    }
}
