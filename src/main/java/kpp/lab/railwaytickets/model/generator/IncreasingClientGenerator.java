package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.configuration.ClientGeneratorProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IncreasingClientGenerator implements BaseClientGenerator {

    private int startGenerationTimeMs;
    private int additionalGenerationTimeMs;

    private static int nextGenerationTimeMs = -1;

    private BaseGeneratorHelper generatorHelper;
    List<BasePosition> entrancePositions;

    public IncreasingClientGenerator() {}

    public void configure(
            int startGenerationTimeMs,
            int additionalGenerationTimeMs,
            BaseGeneratorHelper generatorHelper,
            List<BasePosition> entrancePositions
    ) {
        this.startGenerationTimeMs = startGenerationTimeMs;
        this.additionalGenerationTimeMs = additionalGenerationTimeMs;
        this.generatorHelper = generatorHelper;
        this.entrancePositions = entrancePositions;

        nextGenerationTimeMs = nextGenerationTimeMs == -1 ? startGenerationTimeMs : nextGenerationTimeMs;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(nextGenerationTimeMs);
        nextGenerationTimeMs += additionalGenerationTimeMs;
        return generatorHelper.createClient(entrancePositions);
    }
}
