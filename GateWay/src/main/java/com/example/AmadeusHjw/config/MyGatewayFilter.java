package com.example.AmadeusHjw.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
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
        log.info("请求URL:" + url);
        log.info("method:" + exchange.getRequest().getMethod());

        String secret = exchange.getRequest().getHeaders().getFirst("secret");
//        if (StringUtils.isBlank(secret))
//        {
//            return chain.filter(exchange);
//        }

        //获取param 请求参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        //获取header
        String userId = exchange.getRequest().getHeaders().getFirst("user-id");
        log.info("userId：" + userId);

        /*if (StringUtils.isBlank(userId))
        {
            log.info("*****头部验证不通过，请在头部输入  user-id");
            //终止请求，直接回应
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 0;
    }

}

