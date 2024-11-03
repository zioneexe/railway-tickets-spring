package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.BaseTrainStation;

public interface BaseDirector {

    BaseTrainStation createTrainStation(BaseBuilder builder);
}
