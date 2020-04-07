package com.deepspc.filtergate.config;

import com.deepspc.filtergate.config.properties.AppNameProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.application")
    public AppNameProperties appNameProperties() {
        return new AppNameProperties();
    }
}
