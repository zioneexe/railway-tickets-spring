package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.configuration.ClientGeneratorProperties;
import kpp.lab.railwaytickets.dto.ClientGeneratorDto;
import kpp.lab.railwaytickets.model.generator.*;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ClientGeneratorMapper {

    private static ClientGeneratorProperties clientGeneratorProperties;

    public ClientGeneratorMapper(ClientGeneratorProperties clientGeneratorProperties) {
        ClientGeneratorMapper.clientGeneratorProperties = clientGeneratorProperties;
    }

    public static BaseClientGenerator clientGeneratorDtoToBaseClientGenerator(
            ClientGeneratorDto generatorDto, List<BasePosition> entrancePositions) {

        BaseGeneratorHelper generatorHelper = new GeneratorHelper(clientGeneratorProperties);

        if (Objects.equals(generatorDto.getGeneratorType(), "equal")) {
            EqualIntervalsClientGenerator equalGenerator = new EqualIntervalsClientGenerator();
            equalGenerator.configure(
                    clientGeneratorProperties.getEqualIntervals().getTimeMs(),
                    generatorHelper,
                    entrancePositions
            );
            return equalGenerator;
        }

        if (Objects.equals(generatorDto.getGeneratorType(), "random")) {
            RandomIntervalsClientGenerator randomGenerator = new RandomIntervalsClientGenerator();
            randomGenerator.configure(
                    clientGeneratorProperties.getRandomIntervals().getMinGenerationTimeMs(),
                    clientGeneratorProperties.getRandomIntervals().getMaxGenerationTimeMs(),
                    generatorHelper,
                    entrancePositions
            );
            return randomGenerator;
        }

        if (Objects.equals(generatorDto.getGeneratorType(), "increasing")) {
            IncreasingClientGenerator increasingGenerator = new IncreasingClientGenerator();
            increasingGenerator.configure(
                    clientGeneratorProperties.getIncreasing().getStartGenerationTimeMs(),
                    clientGeneratorProperties.getIncreasing().getAdditionalGenerationTimeMs(),
                    generatorHelper,
                    entrancePositions
            );
            return increasingGenerator;
        }

        throw new IllegalArgumentException("Unsupported generator type: " + generatorDto.getGeneratorType());
    }
}
