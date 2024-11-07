package kpp.lab.railwaytickets.dto;

import kpp.lab.railwaytickets.model.BasePosition;
import kpp.lab.railwaytickets.model.StartupProperties;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

import java.util.List;

public class StartupPropertiesDto {

    private int cashDesksNumber;
    private List<PositionDto> deskPositions;
    private int entrancesNumber;
    private int minServiceTime;
    private int maxServiceTime;
    private ClientGeneratorDto clientGenerator;

    public int getCashDesksNumber() {
        return cashDesksNumber;
    }

    public void setCashDesksNumber(int number) {
        this.cashDesksNumber = number;
    }

    public List<PositionDto> getDeskPositions() {
        return deskPositions;
    }

    public void setDeskPositions(List<PositionDto> positions) {
        this.deskPositions = positions;
    }

    public int getEntrancesNumber() {
        return entrancesNumber;
    }

    public void setEntrancesNumber(int number) {
        this.entrancesNumber = number;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public void setMinServiceTime(int timeMs) {
        this.minServiceTime = timeMs;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public void setMaxServiceTime(int timeMs) {
        this.maxServiceTime = timeMs;
    }

    public ClientGeneratorDto getClientGenerator() {
        return clientGenerator;
    }

    public void setClientGenerator(ClientGeneratorDto generator) { this.clientGenerator = generator; }

    public static class PositionDto {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static class ClientGeneratorDto {
        private String generatorType;

        public String getGeneratorType() {
            return generatorType;
        }

        public void setGeneratorType(String generatorType) {
            this.generatorType = generatorType;
        }
    }
}
