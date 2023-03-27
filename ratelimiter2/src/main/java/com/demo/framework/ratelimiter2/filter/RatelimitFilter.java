package com.demo.framework.ratelimiter2.filter;

import com.demo.framework.ratelimiter2.config.LimitConfigProperties;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RatelimitFilter implements Filter {

    private RateLimiter limiter = null;

    @Autowired
    private LimitConfigProperties limitConfigProperties;

    @Override
    public void init(FilterConfig config) throws ServletException {
        limiter = RateLimiter.create(limitConfigProperties.getPermitsPerSecond());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (limiter.tryAcquire()) {
            log.info("rate数：" + limiter.getRate());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("限流了！！！！！！！！！！！！！！！！");
        }
    }
}
