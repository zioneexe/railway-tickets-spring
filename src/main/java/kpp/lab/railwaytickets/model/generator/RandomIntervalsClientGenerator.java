package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.decorator.*;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import kpp.lab.railwaytickets.services.implementations.ClientCreatorServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    @Value("${clientGenerator.randomIntervals.minGenerationTimeMs}")
    private int minGenerationTimeMs;
    @Value("${clientGenerator.randomIntervals.maxGenerationTimeMs}")
    private int maxGenerationTimeMs;

    private BaseGeneratorHelper generatorHelper;
    List<BasePosition> entrancePositions;

    public RandomIntervalsClientGenerator(List<BasePosition> entrancePositions) {
        this.generatorHelper = new GeneratorHelper();
        this.entrancePositions = entrancePositions;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(generatorHelper.getRandomBetween(minGenerationTimeMs, maxGenerationTimeMs));
        return generatorHelper.createClient(entrancePositions);
    }
}
