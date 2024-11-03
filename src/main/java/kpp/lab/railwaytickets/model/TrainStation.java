package kpp.lab.railwaytickets.model;

import java.util.List;

public class TrainStation implements BaseTrainStation {

    private List<BaseCashDesk> cashDesks;
    private List<BaseEntrance> entrances;
    private BaseMap buildingMap;
    private int maxPeopleCount;

    public TrainStation(List<BaseCashDesk> cashDesks, List<BaseEntrance> entrances, BaseMap map, int maxPeopleCount) {
        this.cashDesks = cashDesks;
        this.entrances = entrances;
        this.buildingMap = map;
        this.maxPeopleCount = maxPeopleCount;
    }

    @Override
    public BaseMap getMap() {
        return buildingMap;
    }

    @Override
    public List<BaseCashDesk> getCashDesks() {
        return cashDesks;
    }

    @Override
    public List<BaseEntrance> getEntrances() {
        return entrances;
    }

    @Override
    public int getMaxPeopleCount() {
        return maxPeopleCount;
    }
}
