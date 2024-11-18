package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class IncreasingClientGenerator implements BaseClientGenerator {

    private int startGenerationTimeMs = 2000;
    private int additionalGenerationTimeMs = 10;

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
