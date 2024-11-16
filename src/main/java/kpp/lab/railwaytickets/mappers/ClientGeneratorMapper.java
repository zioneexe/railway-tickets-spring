package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.ClientGeneratorDto;
import kpp.lab.railwaytickets.dto.PositionDto;
import kpp.lab.railwaytickets.model.BasePosition;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.generator.EqualIntervalsCientGenerator;
import kpp.lab.railwaytickets.model.generator.OverwhelmedClientGenerator;
import kpp.lab.railwaytickets.model.generator.RandomIntervalsClientGenerator;

import java.util.List;
import java.util.Objects;

public class ClientGeneratorMapper {
    public static BaseClientGenerator clientGeneratorDtoToBaseClientGenerator(ClientGeneratorDto generatorDto, int minServiceTime, int maxServiceTime, List<BasePosition> entrancePositions) {
        if (Objects.equals(generatorDto.getGeneratorType(), "equal"))
        {
            return new EqualIntervalsCientGenerator();
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "overwhelmed"))
        {
            return new OverwhelmedClientGenerator();
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "random"))
        {
            return new RandomIntervalsClientGenerator(minServiceTime, maxServiceTime, entrancePositions);
        }

        return null;
    }
}
