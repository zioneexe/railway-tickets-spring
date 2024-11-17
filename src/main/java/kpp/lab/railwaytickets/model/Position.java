package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;

public class Position implements BasePosition {

    // TODO: на UML конструктор

    private int X;

    private int Y;

    public Position(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }
}
