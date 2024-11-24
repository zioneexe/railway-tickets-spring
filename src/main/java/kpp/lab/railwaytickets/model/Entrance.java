package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BaseEntrance;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

public class Entrance implements BaseEntrance {

    private final BasePosition position;

    public Entrance(BasePosition position) {
        this.position = position;
    }

    @Override
    public BasePosition getPosition() {
        return position;
    }
}
