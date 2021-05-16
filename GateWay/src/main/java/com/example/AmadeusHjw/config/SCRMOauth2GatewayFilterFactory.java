package com.example.AmadeusHjw.config;

import com.google.common.collect.Lists;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SCRMOauth2GatewayFilterFactory extends AbstractGatewayFilterFactory<SCRMOauth2GatewayFilterFactory.Config> {
    public SCRMOauth2GatewayFilterFactory(){
        super(Config.class);
    }
    @Override
    public List<String> shortcutFieldOrder() {
        return Lists.newArrayList("SCRMOauth2");
    }
    @Override
    public GatewayFilter apply(Config config) {
        return new SCRMOauth2GatewayFilter();
    }

    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

