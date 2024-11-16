package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseEntrance;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class Entrance implements BaseEntrance {

    private BasePosition position;

    public Entrance(BasePosition position) {
        this.position = position;
    }

    @Override
    public BasePosition getPosition() {
        return position;
    }
}
