package kpp.lab.railwaytickets.model;

public class Map implements BaseMap {

    // TODO: на UML конструктор

    private int sizeX;

    private int sizeY;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public int getSizeX() {
        return 0;
    }

    @Override
    public int getSizeY() {
        return 0;
    }
}
