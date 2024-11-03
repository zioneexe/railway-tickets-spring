package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

import java.util.List;

public class StartupProperties implements BaseStartupProperties{

    private int cashDesksNumber;
    private List<Position> deskPositions;
    private int entrancesNumber;
    private int minServiceTime;
    private int maxServiceTime;
    private BaseClientGenerator clientGenerator;

    private static StartupProperties instance;

    private StartupProperties() {}

    // TODO: багатопотоковий
    public static StartupProperties getInstance() {
        if (instance == null) {
            instance = new StartupProperties();
        }
        return instance;
    }

    @Override
    public int getCashDesksNumber() {
        return cashDesksNumber;
    }

    @Override
    public void setCashDesksNumber(int number) {
        this.cashDesksNumber = number;
    }

    @Override
    public List<Position> getDeskPositions() {
        return deskPositions;
    }

    @Override
    public void setDeskPositions(List<Position> positions) {
        this.deskPositions = positions;
    }

    @Override
    public int getEntrancesNumber() {
        return entrancesNumber;
    }

    @Override
    public void setEntrancesNumber(int number) {
        this.entrancesNumber = number;
    }

    @Override
    public int getMinServiceTime() {
        return minServiceTime;
    }

    @Override
    public void setMinServiceTime(int timeMs) {
        this.minServiceTime = timeMs;
    }

    @Override
    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    @Override
    public void setMaxServiceTime(int timeMs) {
        this.maxServiceTime = timeMs;
    }

    @Override
    public BaseClientGenerator getClientGenerator() {
        return clientGenerator;
    }

    @Override
    public void setClientGenerator(BaseClientGenerator generator) {
        this.clientGenerator = generator;
    }
}
