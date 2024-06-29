package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class Config {
    @Bean
    public ApiClientConfiguration apiClientConfiguration(ConfigurableEnvironment environment) {
        return new ApiClientConfiguration(environment);
    }
}
