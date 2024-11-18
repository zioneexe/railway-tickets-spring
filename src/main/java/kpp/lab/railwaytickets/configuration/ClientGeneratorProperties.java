package kpp.lab.railwaytickets.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "client-generator")
public class ClientGeneratorProperties {

    private EqualIntervals equalIntervals;
    private RandomIntervals randomIntervals;
    private Increasing increasing;
    private int minClientTicketsNumber;
    private int maxClientTicketsNumber;
    private double soldierDecoratorChance;
    private double disabledDecoratorChance;
    private double studentDecoratorChance;
    private double withChildDecoratorChance;

    @Getter
    @Setter
    public static class EqualIntervals {
        private int timeMs;
    }

    @Getter
    @Setter
    public static class RandomIntervals {
        private int minGenerationTimeMs;
        private int maxGenerationTimeMs;
    }

    @Getter
    @Setter
    public static class Increasing {
        private int startGenerationTimeMs;
        private int additionalGenerationTimeMs;
    }
}
