package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.BaseMap;
import kpp.lab.railwaytickets.model.BasePosition;

public interface BaseBuilder {

    void reset();

    void addCashDesk(BasePosition position, int quantity);

    void addEntrance(BasePosition basePosition);

    void addMaxClientNumber(int number);

    void addMap(BaseMap map);
}
