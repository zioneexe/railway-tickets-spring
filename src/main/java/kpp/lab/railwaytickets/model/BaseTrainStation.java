package kpp.lab.railwaytickets.model;

import java.util.List;

public interface BaseTrainStation {

     BaseMap getMap();

     List<BaseCashDesk> getCashDesks();

     List<BaseEntrance> getEntrances();

     int getMaxPeopleCount();

     void addCashDesk(BasePosition position);

     void addBackUpCashDesk(BasePosition position);

     void addEntrance(BasePosition position);

     void setMaxClientNumber(int number);

     void addMap(int sizeX, int sizeY);
}
