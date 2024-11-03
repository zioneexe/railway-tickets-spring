package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.BaseStartupProperties;
import kpp.lab.railwaytickets.model.BaseTrainStation;

public class Director implements BaseDirector {

    private BaseStartupProperties startupProperties;

    public Director(BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
    }

    @Override
    public BaseTrainStation createTrainStation(BaseBuilder builder) {
        return null;
    }
}
