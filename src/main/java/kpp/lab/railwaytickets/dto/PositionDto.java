package kpp.lab.railwaytickets.dto;

public class PositionDto {
    private int x;
    private int y;

    public PositionDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
