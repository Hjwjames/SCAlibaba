package com.example.AmadeusHjw.feign;

import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public FeignClientFallback feignClientFallback() {
        return new FeignClientFallback();
    }

}
