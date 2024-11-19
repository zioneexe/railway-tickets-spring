package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.config.ConfigFileGetter;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.List;

public class EqualIntervalsClientGenerator implements BaseClientGenerator {

    private final int generationTimeMs;

    private final BaseGeneratorHelper generatorHelper;
    private final List<BasePosition> entrancePositions;

    public EqualIntervalsClientGenerator(List<BasePosition> entrancePositions) {
        this.entrancePositions = entrancePositions;

        this.generatorHelper = new GeneratorHelper();
        this.generationTimeMs = ConfigFileGetter.get("clientGenerator.equal.generationTimeMs", int.class);
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(generationTimeMs);
        return generatorHelper.createClient(entrancePositions);
    }
}
