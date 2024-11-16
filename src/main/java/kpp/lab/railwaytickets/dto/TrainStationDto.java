package kpp.lab.railwaytickets.dto;

import kpp.lab.railwaytickets.model.*;

import java.util.List;
import java.util.PriorityQueue;

public class TrainStationDto {
    private List<CashDeskDto> cashDesks;
    private List<EntranceDto> entrances;
    private MapDto buildingMap;
    private int maxPeopleCount;

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
