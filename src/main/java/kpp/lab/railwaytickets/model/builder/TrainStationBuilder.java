package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.*;

import java.util.List;

public class TrainStationBuilder implements BaseBuilder {
    private BaseTrainStation trainStation;

    public TrainStationBuilder() {
        trainStation = new TrainStation();
    }

    @Override
    public void reset() {
        trainStation = new TrainStation();
    }

    @Override
    public void addCashDesk(BasePosition position) {
        trainStation.addCashDesk(position);
    }

    @Override
    public void addReserveCashDesk(BasePosition position){
        trainStation.addBackUpCashDesk(position);
    }

    @Override
    public void addEntrance(BasePosition basePosition) {
        trainStation.addEntrance(basePosition);
    }

    @Override
    public void addMaxClientNumber(int number) {
        trainStation.setMaxClientNumber(number);
    }

    @Override
    public void addMap(int sizeX, int sizeY) { trainStation.addMap(sizeX, sizeY); }

    public BaseTrainStation getResult() {
        return trainStation;
    }
}