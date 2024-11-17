package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;

public interface BaseBuilder {

    void reset();

    void addCashDesk(BasePosition position);

    void addReserveCashDesk(BasePosition position);

    void addEntrance(BasePosition basePosition);

    void addMaxClientNumber(int number);

    void addMap(int sizeX, int sizeY);

    BaseTrainStation getResult();
}
