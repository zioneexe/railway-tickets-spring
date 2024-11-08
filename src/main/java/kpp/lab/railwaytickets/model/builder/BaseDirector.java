package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.BaseTrainStation;

public interface BaseDirector {

    void createTrainStation(BaseBuilder builder, String comand);
}
