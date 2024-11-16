package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.ClientGeneratorDto;
import kpp.lab.railwaytickets.model.generator.*;


import java.util.Objects;

public class ClientGeneratorMapper {
    public static BaseClientGenerator clientGeneratorDtoToBaseClientGenerator(ClientGeneratorDto generatorDto, int minServiceTime, int maxServiceTime, List<BasePosition> entrancePositions) {
        if (Objects.equals(generatorDto.getGeneratorType(), "equal"))
        {
            return new EqualIntervalsClientGenerator();
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "overwhelmed"))
        {
            return new OverwhelmedClientGenerator(cashDeskService);
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "random"))
        {
            return new RandomIntervalsClientGenerator(cashDeskService);
        }

        return null;
    }
}
