package kpp.lab.railwaytickets.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StartupPropertiesDto {
    private List<PositionDto> deskPositions;
    private List<PositionDto> entrancePositions;
    private PositionDto reserveDeskPosition;
    private int minServiceTime;
    private int maxServiceTime;
    private ClientGeneratorDto clientGenerator;
    private int maxClientNumber;
    private int stationWidth;
    private int stationHeight;
}
