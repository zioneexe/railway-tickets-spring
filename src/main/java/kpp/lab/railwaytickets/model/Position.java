package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;

public class Position implements BasePosition {

    private final int x;

    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
