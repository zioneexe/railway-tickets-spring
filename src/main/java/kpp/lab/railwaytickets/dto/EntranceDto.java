package kpp.lab.railwaytickets.dto;

import kpp.lab.railwaytickets.model.BasePosition;

public class EntranceDto {

    private PositionDto position;

    public EntranceDto(PositionDto position) {
        this.position = position;
    }

    public PositionDto getPosition() {
        return position;
    }
}
