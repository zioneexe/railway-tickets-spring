package kpp.lab.railwaytickets.configuration;

import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(ClientGeneratorProperties.class)
public class AppConfig {

    @Bean
    public List<BasePosition> entrancePositions() {
        return List.of(new Position(0, 0), new Position(1, 1));
    }
}
