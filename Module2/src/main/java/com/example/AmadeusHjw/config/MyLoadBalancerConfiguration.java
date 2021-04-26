package com.example.AmadeusHjw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

public class MyLoadBalancerConfiguration {
//    @Bean
//    @ConditionalOnMissingBean
//    public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(
//            Environment environment,
//            LoadBalancerClientFactory loadBalancerClientFactory) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
//                ServiceInstanceListSupplier.class), name);
//    }
//    static class RandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {
//
//        private static final Logger log = LoggerFactory
//                .getLogger(RandomLoadBalancer.class);
//
//        private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
//
//        private final String serviceId;
//
//        private final Random random;
//
//        RandomLoadBalancer(
//                ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider,
//                String serviceId) {
//            this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
//            this.serviceId = serviceId;
//            this.random = new Random();
//        }
//
//        @Override
//        public Mono<Response<ServiceInstance>> choose(Request request) {
//            log.info("random spring cloud loadbalacer active -.-");
//            ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
//                    .getIfAvailable(NoopServiceInstanceListSupplier::new);
//            return supplier.get().next().map(this::getInstanceResponse);
//        }
//
//        private Response<ServiceInstance> getInstanceResponse(
//                List<ServiceInstance> instances) {
//            if (instances.isEmpty()) {
//                return new EmptyResponse();
//            }
//            ServiceInstance instance = instances.get(random.nextInt(instances.size()));
//
//            return new DefaultResponse(instance);
//        }
//
//    }
}
