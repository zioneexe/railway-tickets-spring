package kpp.lab.railwaytickets.model;

import java.util.List;

public interface BaseTrainStation {

     BaseMap getMap();

     List<BaseCashDesk> getCashDesks();

     List<BaseEntrance> getEntrances();

     int getMaxPeopleCount();

}
