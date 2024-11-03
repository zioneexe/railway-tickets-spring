package kpp.lab.railwaytickets.model;

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
