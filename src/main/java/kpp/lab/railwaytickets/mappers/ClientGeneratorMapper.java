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

    public static BaseClientGenerator clientGeneratorDtoToBaseClientGenerator(ClientGeneratorDto generatorDto,
                                                                              List<BasePosition> entrancePositions) {

        if (Objects.equals(generatorDto.getGeneratorType(), "equal"))
        {
            return new EqualIntervalsClientGenerator(entrancePositions);
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "increasing"))
        {
            return new IncreasingClientGenerator(entrancePositions);
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "random"))
        {
            return new RandomIntervalsClientGenerator(entrancePositions);
        }

        return null;
    }
}
