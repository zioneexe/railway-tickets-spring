package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupProperties implements BaseStartupProperties {

    private List<BasePosition> deskPositions = new ArrayList<>();
    private List<BasePosition> entrancePositions = new ArrayList<>();
    private BasePosition reserveDeskPosition;

    private int minServiceTime;
    private int maxServiceTime;

    private BaseClientGenerator clientGenerator;
    private int maxClientNumber;

    private int stationWidth;
    private int stationHeight;

    // Шось придумаєм і юсіді
    private static StartupProperties instance;

    private StartupProperties() {
    }

    // TODO: багатопотоковий
    public static StartupProperties getInstance() {
        if (instance == null) {
            instance = new StartupProperties();
        }
        return instance;
    }
    // Шось придумаєм і юсіді

    @Override
    public int getStationWidth() {
        return stationWidth;
    }

    @Override
    public void setStationWidth(int width) {
        this.stationWidth = width;
    }

    @Override
    public int getStationHeight() {
        return stationHeight;
    }

    @Override
    public void setStationHeight(int height) {
        this.stationHeight = height;
    }

    @Override
    public List<BasePosition> getDeskPositions() {
        return deskPositions;
    }

    @Override
    public void setDeskPositions(List<BasePosition> positions) {
        this.deskPositions = positions;
    }

    @Override
    public BasePosition getReserveDeskPosition() {
        return reserveDeskPosition;
    }

    @Override
    public void setReserveDeskPosition(BasePosition position) {
        this.reserveDeskPosition = position;
    }

    @Override
    public List<BasePosition> getEntrancePositions() {
        return entrancePositions;
    }

    @Override
    public void setEntrancePositions(List<BasePosition> positions) {
        this.entrancePositions = positions;
    }

    @Override
    public int getMinServiceTime() {
        return minServiceTime;
    }

    @Override
    public void setMinServiceTime(int timeMs) {
        this.minServiceTime = timeMs;
    }

    @Override
    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    @Override
    public void setMaxServiceTime(int timeMs) {
        this.maxServiceTime = timeMs;
    }

    @Override
    public BaseClientGenerator getClientGenerator() {
        return clientGenerator;
    }

    @Override
    public void setClientGenerator(BaseClientGenerator generator) {
        this.clientGenerator = generator;
    }

    @Override
    public int getMaxClientNumber() {
        return maxClientNumber;
    }

    @Override
    public void setMaxClientNumber(int maxClientNumber) {
        this.maxClientNumber = maxClientNumber;
    }
}
