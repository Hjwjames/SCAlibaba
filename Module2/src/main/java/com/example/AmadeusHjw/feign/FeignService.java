package com.example.AmadeusHjw.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Amadeus-Module1", fallback = FeignClientFallback.class,
        configuration = FeignConfiguration.class)
public interface FeignService {
    @GetMapping(value = "/test")
    String test();

    @GetMapping(value = "/notfound")
    String notfound();
}
