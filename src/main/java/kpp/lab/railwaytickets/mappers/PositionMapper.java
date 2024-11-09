package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.CashDeskDto;
import kpp.lab.railwaytickets.dto.PositionDto;
import kpp.lab.railwaytickets.model.BaseCashDesk;
import kpp.lab.railwaytickets.model.BasePosition;

public class PositionMapper {
    public static PositionDto basePositionToPositionDto(BasePosition position) {
        return new PositionDto(
                position.getX(),
                position.getY()
        );
    }
}
