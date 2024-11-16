package kpp.lab.railwaytickets.model.builder;

import kpp.lab.railwaytickets.model.abstractions.BaseStartupProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Director implements BaseDirector {

    private BaseStartupProperties startupProperties;

    @Autowired
    public Director(BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
    }

    @Override
    public void createTrainStation(BaseBuilder builder) {
        builder.reset();
        builder.addReserveCashDesk(startupProperties.getReserveDeskPosition());
        builder.addMaxClientNumber(startupProperties.getMaxClientNumber());
        builder.addMap(startupProperties.getStationWidth(), startupProperties.getStationHeight());

        var entrancePositions = startupProperties.getEntrancePositions();
        for (var entrancePosition : entrancePositions) {
            builder.addEntrance(entrancePosition);
        }

        var deskPositions = startupProperties.getDeskPositions();
        for (var deskPosition : deskPositions) {
            builder.addCashDesk(deskPosition);
        }
    }
}
