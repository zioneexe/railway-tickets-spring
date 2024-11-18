package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EqualIntervalsClientGenerator implements BaseClientGenerator {

    private int generationTimeMs = 30;

    private BaseGeneratorHelper generatorHelper;
    List<BasePosition> entrancePositions;

    public EqualIntervalsClientGenerator(List<BasePosition> entrancePositions) {
        this.generatorHelper = new GeneratorHelper();
        this.entrancePositions = entrancePositions;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        generatorHelper.waitFor(generationTimeMs);
        return generatorHelper.createClient(entrancePositions);
    }
}
