package com.microservice.apttrade.api.service;

import feign.Client;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientsConfiguration.class)
public class TestFeignConfiguration {
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}
