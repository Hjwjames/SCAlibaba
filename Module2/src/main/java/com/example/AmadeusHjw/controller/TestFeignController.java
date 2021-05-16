package com.example.AmadeusHjw.controller;

import com.example.AmadeusHjw.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeignController {
    @Autowired
    private FeignService feignClient;
    @GetMapping(value = "/testFeign")
    public String testFeign(){
        return feignClient.test();
    }
    @GetMapping(value = "/oauth/testFeign/filter")
    public String testFeignfilter2(){
        return feignClient.test();
    }
    @GetMapping(value = "/notfound")
    public String notfound(){
        return feignClient.notfound();
    }
    @GetMapping(value = "/getNacosConfig")
    public String getNacosConfig(){
        return feignClient.getNacosConfig();
    }
}
