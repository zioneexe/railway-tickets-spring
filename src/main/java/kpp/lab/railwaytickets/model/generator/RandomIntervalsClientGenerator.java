package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.configuration.ClientGeneratorProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    private int minGenerationTimeMs;
    private int maxGenerationTimeMs;

    private BaseGeneratorHelper generatorHelper;
    private List<BasePosition> entrancePositions;

    public RandomIntervalsClientGenerator() {}

    public void configure(
            int minGenerationTimeMs,
            int maxGenerationTimeMs,
            BaseGeneratorHelper generatorHelper,
            List<BasePosition> entrancePositions
    ) {
        this.minGenerationTimeMs = minGenerationTimeMs;
        this.maxGenerationTimeMs = maxGenerationTimeMs;
        this.generatorHelper = generatorHelper;
        this.entrancePositions = entrancePositions;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(generatorHelper.getRandomBetween(minGenerationTimeMs, maxGenerationTimeMs));
        return generatorHelper.createClient(entrancePositions);
    }
}
