package kpp.lab.railwaytickets.dto;

public class EntranceDto {

    private final PositionDto position;

    public EntranceDto(PositionDto position) {
        this.position = position;
    }

    public PositionDto getPosition() {
        return position;
    }
}
