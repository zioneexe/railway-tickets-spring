package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

import java.util.List;

public interface BaseStartupProperties {

    int getCashDesksNumber();
    void setCashDesksNumber(int number);

    List<Position> getDeskPositions();
    void setDeskPositions(List<Position> positions);

    int getEntrancesNumber();
    void setEntrancesNumber(int number);

    int getMinServiceTime();
    void setMinServiceTime(int timeMs);

    int getMaxServiceTime();
    void setMaxServiceTime(int timeMs);

    BaseClientGenerator getClientGenerator();
    void setClientGenerator(BaseClientGenerator generator);
}
