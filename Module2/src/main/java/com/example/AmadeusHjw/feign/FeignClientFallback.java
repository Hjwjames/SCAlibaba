package com.example.AmadeusHjw.feign;

public class FeignClientFallback implements FeignService {
    @Override
    public String test() {
        return "echo fallback";
    }

    @Override
    public String notfound() {
        return "notFound fallback";
    }

    @Override
    public String getNacosConfig() {
        return "notFound fallback";
    }
}
