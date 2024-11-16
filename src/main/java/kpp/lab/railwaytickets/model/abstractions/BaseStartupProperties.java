package kpp.lab.railwaytickets.model.abstractions;

import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

import java.util.List;

public interface BaseStartupProperties {

    int getStationWidth();
    void setStationWidth(int width);

    int getStationHeight();
    void setStationHeight(int height);

    List<BasePosition> getDeskPositions();
    void setDeskPositions(List<BasePosition> positions);

    BasePosition getReserveDeskPosition();
    void setReserveDeskPosition(BasePosition position);

    List<BasePosition> getEntrancePositions();
    void setEntrancePositions(List<BasePosition> positions);

    int getMinServiceTime();
    void setMinServiceTime(int timeMs);

    int getMaxServiceTime();
    void setMaxServiceTime(int timeMs);

    BaseClientGenerator getClientGenerator();
    void setClientGenerator(BaseClientGenerator generator);
}
