package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.config.ConfigFileGetter;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.List;

public class IncreasingClientGenerator implements BaseClientGenerator {

    private final int startGenerationTimeMs;
    private final int incrementTimeMs ;
    private static int nextGenerationTimeMs = -1;

    private final BaseGeneratorHelper generatorHelper;
    List<BasePosition> entrancePositions;

    public IncreasingClientGenerator(List<BasePosition> entrancePositions) {
        this.entrancePositions = entrancePositions;

        this.generatorHelper = new GeneratorHelper();
        this.startGenerationTimeMs = ConfigFileGetter.get("clientGenerator.increasing.startGenerationTimeMs", int.class);
        this.incrementTimeMs = ConfigFileGetter.get("clientGenerator.increasing.incrementTimeMs", int.class);

        nextGenerationTimeMs = nextGenerationTimeMs == -1 ? startGenerationTimeMs : nextGenerationTimeMs;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(nextGenerationTimeMs);
        nextGenerationTimeMs += incrementTimeMs;
        return generatorHelper.createClient(entrancePositions);
    }
}
