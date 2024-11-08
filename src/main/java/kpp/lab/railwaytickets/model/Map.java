package kpp.lab.railwaytickets.model;

public class Map implements BaseMap {

    // TODO: на UML конструктор

    private int sizeX;

    private int sizeY;

    public Map() {
        this.sizeX = StartupProperties.getInstance().getStationWidth();
        this.sizeY = StartupProperties.getInstance().getStationHeight();
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
