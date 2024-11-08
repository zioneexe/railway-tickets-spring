package kpp.lab.railwaytickets.model;

import java.util.ArrayList;
import java.util.List;

public class TrainStation implements BaseTrainStation {

    private List<BaseCashDesk> cashDesks;
    private List<BaseEntrance> entrances;
    private BaseMap buildingMap;
    private int maxPeopleCount;

    public TrainStation() {
        cashDesks = new ArrayList<>();
        entrances = new ArrayList<>();
        buildingMap = new Map();
        maxPeopleCount = 0;
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

    @Override
    public void addCashDesk(BasePosition position) {
        cashDesks.add(new CashDesk(position, false));
    }

    @Override
    public void addBackUpCashDesk(BasePosition position) {
        cashDesks.add(new CashDesk(position, true));
    }

    public void addEntrance(BasePosition position) {
        entrances.add(new Entrance(position));
    }

    public void setMaxClientNumber(int number) {
        maxPeopleCount = number;
    }
}
