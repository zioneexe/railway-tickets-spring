package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    private int minGenerationTimeMs = 1000;
    private int maxGenerationTimeMs = 5000;

    private BaseGeneratorHelper generatorHelper;
    private List<BasePosition> entrancePositions;

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
