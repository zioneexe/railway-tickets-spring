package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.ClientGeneratorDto;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.generator.EqualIntervalsClientGenerator;
import kpp.lab.railwaytickets.model.generator.OverwhelmedClientGenerator;
import kpp.lab.railwaytickets.model.generator.RandomIntervalsClientGenerator;

import java.util.Objects;

public class ClientGeneratorMapper {
    public static BaseClientGenerator clientGeneratorDtoToBaseClientGenerator(ClientGeneratorDto generatorDto) {
        if (Objects.equals(generatorDto.getGeneratorType(), "equal"))
        {
            return new EqualIntervalsClientGenerator();
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "overwhelmed"))
        {
            return new OverwhelmedClientGenerator();
        }
        if (Objects.equals(generatorDto.getGeneratorType(), "random"))
        {
            return new RandomIntervalsClientGenerator();
        }

        return null;
    }
}
