package kpp.lab.railwaytickets.validation;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartupPropertiesValidator {

    private StartupPropertiesValidator() {}

    private static final Set<String> violations = new HashSet<>();

    public static Set<String> validate(BaseStartupProperties properties) {

        int height = properties.getStationHeight();
        int width = properties.getStationWidth();
        List<BasePosition> deskPositions = properties.getDeskPositions();
        List<BasePosition> entrancePositions = properties.getEntrancePositions();
        BasePosition reserveDeskPosition = properties.getReserveDeskPosition();
        int minServiceTime = properties.getMinServiceTime();
        int maxServiceTime = properties.getMaxServiceTime();

        validateMapSize(height, width);
        validateServiceTime(minServiceTime, maxServiceTime);
        validateDesks(deskPositions);
        validatePositions(entrancePositions);
        validateMapLoad(width, height, deskPositions, entrancePositions);
        validatePositions(width, height, deskPositions, entrancePositions);

        return violations;
    }

    private static void validateMapSize(int height, int width) {
        if (height <= 0 || width <= 0) {
            violations.add("Invalid map size, consider setting height and width  > 0");
        }
    }

    private static void validateServiceTime(int minServiceTime, int maxServiceTime) {
        if (minServiceTime <= 0 || maxServiceTime <= 0) {
            violations.add("Invalid service time, consider setting service time values > 0");
        }

        if (minServiceTime >= maxServiceTime) {
            violations.add("Invalid service time, max service time must be greater than min service time");
        }
    }

    private static void validateDesks(List<BasePosition> deskPositions) {
        if (deskPositions.isEmpty()) {
            violations.add("Invalid desk positions. No desks set");
        }
    }

    private static void validatePositions(List<BasePosition> entrancePositions) {
        if (entrancePositions.isEmpty()) {
            violations.add("Invalid entrance positions. No entrances set");
        }
    }

    private static void validateMapLoad(int width, int height,
                                        List<BasePosition> deskPositions, List<BasePosition> entrancePositions) {
        int occupiedPositionsCount = deskPositions.size() + entrancePositions.size();
        int positionsCount = width * height;

        if (occupiedPositionsCount > positionsCount) {
            violations.add("No free positions. Consider changing map size, or change desk count");
        }
    }

    private static void validatePositions(int width, int height,
                                          List<BasePosition> deskPositions, List<BasePosition> entrancePositions) {
        if (deskPositions.stream().anyMatch(d -> d.getX() < 1 || d.getX() > width
                || d.getY() < 1 || d.getY() > height)) {
            violations.add("Invalid desk position found");
        }

        if (entrancePositions.stream().anyMatch(e -> e.getX() < 1 ||
                e.getX() > width || e.getY() < 1 || e.getY() > height)) {
            violations.add("Invalid entrance position found");
        }
    }
}
