package com.example.AmadeusHjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.AmadeusHjw"})
public class GateWayStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayStartApplication.class, args);
    }

}
