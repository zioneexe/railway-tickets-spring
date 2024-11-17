package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;

public class IncreasingClientGenerator implements BaseClientGenerator {

    @Value("${clientGenerator.overwhelmed.startGenerationTimeMs}")
    private int startGenerationTimeMs;
    @Value("${clientGenerator.overwhelmed.additionalGenerationTimeMs}")
    private int additionalGenerationTimeMs;

    private static int nextGenerationTimeMs = -1;

    private BaseGeneratorHelper generatorHelper;
    List<BasePosition> entrancePositions;

    public IncreasingClientGenerator(List<BasePosition> entrancePositions) {
        this.generatorHelper = new GeneratorHelper();
        this.entrancePositions = entrancePositions;

        this.nextGenerationTimeMs = this.nextGenerationTimeMs == -1 ? startGenerationTimeMs : this.nextGenerationTimeMs;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(nextGenerationTimeMs);
        nextGenerationTimeMs += additionalGenerationTimeMs;
        return generatorHelper.createClient(entrancePositions);
    }
}
