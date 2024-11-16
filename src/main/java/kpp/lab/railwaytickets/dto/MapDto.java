package kpp.lab.railwaytickets.dto;

public class MapDto {

    private final int sizeX;
    private final int sizeY;

    public MapDto(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }
}