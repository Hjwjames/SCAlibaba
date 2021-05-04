package com.example.AmadeusHjw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class MyGlobalFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(MyGlobalFilter.class);
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        log.info("前置逻辑");
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() ->
        {
            Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
            if (startTime != null) {
                log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
            }
            log.info("后置逻辑");
        }));
    }

    //   值越小，优先级越高
//    int HIGHEST_PRECEDENCE = -2147483648;
//    int LOWEST_PRECEDENCE = 2147483647;
    @Override
    public int getOrder()
    {
        return HIGHEST_PRECEDENCE + 100;
    }
}
