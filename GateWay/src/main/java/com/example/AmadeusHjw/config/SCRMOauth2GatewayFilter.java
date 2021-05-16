package com.example.AmadeusHjw.config;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class SCRMOauth2GatewayFilter implements GatewayFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(SCRMOauth2GatewayFilter.class);

    @Autowired
    private NacosConfig nacosConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String customer_token = request.getHeaders().getFirst("customer_token");
        String code = request.getQueryParams().getFirst("code");
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        HttpCookie customer_cookie = cookies.getFirst("customer_cookie");
        String uriString = request.getURI().toString();

        if(null == customer_cookie){
            if(StringUtils.isEmpty(code)){
                //重定向
                String newUri = uriString.toString().replace("gateway",nacosConfig.getDomain());
                String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + nacosConfig.getCorpId()+
                        "&redirect_uri=" + newUri +
                        "&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
                response.setStatusCode(HttpStatus.FOUND);
                response.getHeaders().set(HttpHeaders.LOCATION, url);
                return response.setComplete();
            }else{
                //进入方法，并设置cookie
                ResponseCookie responseCookie = ResponseCookie.from("customer_cookie",getUser(code,exchange)).build();
                exchange.getResponse().addCookie(responseCookie);
                //把request重新包装,继续传递
                URI uri = request.getURI();
                ServerHttpRequest newRequest = request.mutate().uri(uri).build();
                // 定义新的消息头
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(exchange.getRequest().getHeaders());
                headers.remove(HttpHeaders.COOKIE);
                headers.set(HttpHeaders.COOKIE, "customer_cookie="+getUser(code,exchange));
                newRequest = new ServerHttpRequestDecorator(newRequest) {
                    @Override
                    public HttpHeaders getHeaders() {
                        return headers;
                    }
                };
                return chain.filter(exchange.mutate().request(newRequest).build());
            }
        }else{
            //放行
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public String getUser(String code,ServerWebExchange exchange){
        //通过code从企业微信获取员工id
        return "Amadeus";
    }

}


