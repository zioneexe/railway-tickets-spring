package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.configuration.ClientGeneratorProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EqualIntervalsClientGenerator implements BaseClientGenerator {

    private int generationTimeMs;

    private BaseGeneratorHelper generatorHelper;

    List<BasePosition> entrancePositions;

    public EqualIntervalsClientGenerator() {}

    public void configure(
            int generationTimeMs,
            BaseGeneratorHelper generatorHelper,
            List<BasePosition> entrancePositions
    ) {
        this.generationTimeMs = generationTimeMs;
        this.generatorHelper = generatorHelper;
        this.entrancePositions = entrancePositions;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(generationTimeMs);
        return generatorHelper.createClient(entrancePositions);
    }
}
