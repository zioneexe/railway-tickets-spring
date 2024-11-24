package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.ClientGeneratorDto;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.generator.EqualIntervalsClientGenerator;
import kpp.lab.railwaytickets.model.generator.IncreasingClientGenerator;
import kpp.lab.railwaytickets.model.generator.RandomIntervalsClientGenerator;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.List;
import java.util.Objects;

public class ClientGeneratorMapper {

    private ClientGeneratorMapper() {}

    public static final String EQUAL = "equal";
    public static final String INCREASING = "increasing";
    public static final String RANDOM = "random";

    public static BaseClientGenerator clientGeneratorDtoToBaseClientGenerator(ClientGeneratorDto generatorDto,
                                                                              List<BasePosition> entrancePositions) {

        if (Objects.equals(generatorDto.getGeneratorType(), EQUAL))
        {
            return new EqualIntervalsClientGenerator(entrancePositions);
        }

        if (Objects.equals(generatorDto.getGeneratorType(), INCREASING))
        {
            return new IncreasingClientGenerator(entrancePositions);
        }

        if (Objects.equals(generatorDto.getGeneratorType(), RANDOM))
        {
            return new RandomIntervalsClientGenerator(entrancePositions);
        }

        return null;
    }
}
