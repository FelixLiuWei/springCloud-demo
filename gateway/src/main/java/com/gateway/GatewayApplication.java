package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     *
     * 解决网关跨域问题（也可以在配置文件中配置）
     *
     * 普通Consumer服务层：spring-webmvc:CorsFilter  （支持同步阻塞，异步阻塞）
     * 网关层：spring-webflux:CorsWebFilter      （支持异步非阻塞）
     *
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter() {
        //cors跨域对象
        CorsConfiguration config = new CorsConfiguration();

        // #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
        config.addAllowedOrigin("http://localhost:10010");

        // 允许cookies跨域
        config.setAllowCredentials(true);

        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod("*");

        // #允许访问的头信息,*表示全部
        config.addAllowedHeader("*");

        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        config.setMaxAge(3600L);

        //cors过滤对象
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}

