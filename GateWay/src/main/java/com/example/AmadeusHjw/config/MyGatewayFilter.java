package com.example.AmadeusHjw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 参考博文https://www.cnblogs.com/crazymakercircle/p/11704077.html
 */

public class MyGatewayFilter implements GatewayFilter, Ordered{
    private static final Logger log = LoggerFactory.getLogger(MyGatewayFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        String url = exchange.getRequest().getPath().pathWithinApplication().value();
        String param = exchange.getRequest().getQueryParams().toString();
        ServerHttpResponse response = exchange.getResponse();

        log.info("请求URL:" + url);
        log.info("请求URL:" + exchange.getRequest().getURI());
        log.info("method:" + exchange.getRequest().getMethod());

        exchange.getRequest().getPath();

        String secret = exchange.getRequest().getHeaders().getFirst("secret");


        //获取param 请求参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //获取header
        String userId = exchange.getRequest().getHeaders().getFirst("user-id");
        log.info("userId：" + userId);

        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 0;
    }

}

