package com.example.AmadeusHjw.controller;

import com.example.AmadeusHjw.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @Autowired
    private ServerConfig serverConfig;

    @GetMapping(value = "/test")
    public String test(){
        System.out.println("print log at service"+serverConfig.getUrl());
        return "print log at service"+serverConfig.getUrl();
    }
}
