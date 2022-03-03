package com.vedegiska.gateway_microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.vedegiska.gateway_microservice.repo")
@EnableTransactionManagement
public class CoreAppConfiguration { }
