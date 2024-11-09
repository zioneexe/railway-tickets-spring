package kpp.lab.railwaytickets.dto;

import kpp.lab.railwaytickets.model.BaseMap;

public class MapDto {

    private int sizeX;
    private int sizeY;

    public MapDto(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() { return sizeY; }
}