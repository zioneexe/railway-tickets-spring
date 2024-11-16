package kpp.lab.railwaytickets.config;

import kpp.lab.railwaytickets.model.StartupProperties;
import kpp.lab.railwaytickets.model.abstractions.BaseStartupProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BaseStartupProperties startupProperties() {
        return StartupProperties.getInstance();
    }
}