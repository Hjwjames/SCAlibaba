package com.example.AmadeusHjw.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "amadeus-module1", fallback = FeignClientFallback.class,
        configuration = FeignConfiguration.class)
public interface FeignService {
    @GetMapping(value = "/module1/test")
    String test();

    @GetMapping(value = "/module1/notfound")
    String notfound();

    @GetMapping(value = "/module1/getNacosConfig")
    String getNacosConfig();
}
