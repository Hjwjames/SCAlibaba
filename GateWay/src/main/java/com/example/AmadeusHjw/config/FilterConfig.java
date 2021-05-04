package com.example.AmadeusHjw.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 值越小，优先级越高
 * int HIGHEST_PRECEDENCE = -2147483648;
 * int LOWEST_PRECEDENCE = 2147483647;
 */
@Configuration
public class FilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter MyGlobalFilter()
    {
        return new MyGlobalFilter();
    }
}
