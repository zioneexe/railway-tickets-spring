package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.EntranceDto;
import kpp.lab.railwaytickets.dto.PositionDto;
import kpp.lab.railwaytickets.model.BaseEntrance;
import kpp.lab.railwaytickets.model.BasePosition;

public class EntranceMapper {
    public static EntranceDto baseEntranceToEntranceDto(BaseEntrance entrance) {
        return new EntranceDto(
                PositionMapper.basePositionToPositionDto(entrance.getPosition())
        );
    }
}
