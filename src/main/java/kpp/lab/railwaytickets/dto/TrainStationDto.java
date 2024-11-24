package kpp.lab.railwaytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TrainStationDto {
    private final List<CashDeskDto> cashDesks;

    private final List<EntranceDto> entrances;

    private final MapDto buildingMap;

    private final int maxPeopleCount;
}
