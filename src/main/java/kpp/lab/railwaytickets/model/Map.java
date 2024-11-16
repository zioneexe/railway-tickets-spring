package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseMap;

public class Map implements BaseMap {

    // TODO: на UML конструктор

    private int sizeX;
    private int sizeY;

    @Override
    public int getSizeX() { return sizeX; }

    @Override
    public void setSizeX(int sizeX) { this.sizeX = sizeX; }

    @Override
    public int getSizeY() { return sizeY; }

    @Override
    public void setSizeY(int sizeY) { this.sizeY = sizeY; }
}
