package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.PositionDto;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.model.Position;

public class PositionMapper {

    private PositionMapper() {}

    public static PositionDto basePositionToPositionDto(BasePosition position) {
        return new PositionDto(
                position.getX(),
                position.getY()
        );
    }

    public static BasePosition positionDtoToBasePosition(PositionDto positionDto) {
        return new Position(
                positionDto.getX(),
                positionDto.getY()
        );
    }
}
