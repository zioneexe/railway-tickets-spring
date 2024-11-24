package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.EntranceDto;
import kpp.lab.railwaytickets.model.interfaces.BaseEntrance;

public class EntranceMapper {

    private EntranceMapper() {}

    public static EntranceDto baseEntranceToEntranceDto(BaseEntrance entrance) {
        return new EntranceDto(
                PositionMapper.basePositionToPositionDto(entrance.getPosition())
        );
    }
}
