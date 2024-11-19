package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.config.ConfigFileGetter;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.List;

public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    private final int minGenerationTimeMs;
    private final int maxGenerationTimeMs;

    private BaseGeneratorHelper generatorHelper;
    private List<BasePosition> entrancePositions;

    public RandomIntervalsClientGenerator(List<BasePosition> entrancePositions) {
        this.entrancePositions = entrancePositions;

        this.generatorHelper = new GeneratorHelper();
        this.minGenerationTimeMs = ConfigFileGetter.get("clientGenerator.random.minGenerationTimeMs", int.class);
        this.maxGenerationTimeMs = ConfigFileGetter.get("clientGenerator.random.maxGenerationTimeMs", int.class);
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(generatorHelper.getRandomBetween(minGenerationTimeMs, maxGenerationTimeMs));
        return generatorHelper.createClient(entrancePositions);
    }
}
