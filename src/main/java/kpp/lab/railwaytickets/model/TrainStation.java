package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainStation implements BaseTrainStation {

    private final List<BaseCashDesk> cashDesks;
    private final List<BaseEntrance> entrances;
    private BaseMap buildingMap;
    private int maxPeopleCount;

    public TrainStation() {
        cashDesks = new ArrayList<>();
        entrances = new ArrayList<>();
        buildingMap = new Map();
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
    public void addReserveCashDesk(BasePosition position) {
        cashDesks.add(new CashDesk(position, true));
    }

    @Override
    public void addMap(int sizeX, int sizeY) {
        buildingMap.setSizeX(sizeX);
        buildingMap.setSizeY(sizeY);
    }

    public void addEntrance(BasePosition position) {
        entrances.add(new Entrance(position));
    }

    public void setMaxClientNumber(int number) {
        maxPeopleCount = number;
    }

    public int getCurrentClientNumber() {
        return cashDesks.stream().mapToInt(e -> e.getQueue().size()).sum();
    }
}
