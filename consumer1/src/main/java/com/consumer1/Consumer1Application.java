package com.consumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringCloudApplication
@EnableFeignClients
public class Consumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer1Application.class, args);
    }

    /**
     * 跨域配置，解决前端Access-Control-Allow-Origin 错误
     *
     * 普通Consumer服务层：spring-webmvc:CorsFilter  （支持同步阻塞，异步阻塞）
     * 网关层：spring-webflux:CorsWebFilter      （支持异步非阻塞）
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);  // 允许cookies跨域
        corsConfiguration.addAllowedOrigin("*");      // 允许向该服务器提交请求的URI，*表示全部允许。
        corsConfiguration.addAllowedHeader("*");      // 允许访问的头信息,*表示全部
        corsConfiguration.setMaxAge(18000L);          // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        corsConfiguration.addAllowedMethod("*");      // 允许提交请求的方法，*表示全部允许，也可以单独设置GET、PUT等
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
