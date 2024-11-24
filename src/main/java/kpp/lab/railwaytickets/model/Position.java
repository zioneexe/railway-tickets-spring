package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;

public class Position implements BasePosition {

    private int x;

    private int y;

    public Position(int X, int Y) {
        this.x = X;
        this.y = Y;
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
