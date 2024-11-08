package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.BaseStartupProperties;
import kpp.lab.railwaytickets.model.BaseTrainStation;

public class Director implements BaseDirector {

    private BaseStartupProperties startupProperties;
    private BaseBuilder builder;

    public Director(BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
        builder = new TrainStationBuilder();
    }

    @Override
    public void createTrainStation(BaseBuilder builder, String comand) {
        builder.reset();
    }
}
