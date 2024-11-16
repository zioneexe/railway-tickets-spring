package kpp.lab.railwaytickets.dto;

import java.util.List;

public class TrainStationDto {
    private final List<CashDeskDto> cashDesks;
    private final List<EntranceDto> entrances;
    private final MapDto buildingMap;
    private final int maxPeopleCount;

    public TrainStationDto(List<CashDeskDto> cashDesks, List<EntranceDto> entrances, MapDto buildingMap, int maxPeopleCount) {
        this.cashDesks = cashDesks;
        this.entrances = entrances;
        this.buildingMap = buildingMap;
        this.maxPeopleCount = maxPeopleCount;
    }

    public MapDto getMap() { return buildingMap; }

    public List<CashDeskDto> getCashDesks() { return cashDesks; }

    public List<EntranceDto> getEntrances() {return entrances; }

    public int getMaxPeopleCount() { return maxPeopleCount; }
}
