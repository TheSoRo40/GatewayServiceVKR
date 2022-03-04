package com.vedegiska.gateway_microservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableJpaRepositories("com.vedegiska.gateway_microservice.repo")
@EnableTransactionManagement
public class CoreAppConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(2L))
                .setReadTimeout(Duration.ofSeconds(4L))
                .build();
    }

}
