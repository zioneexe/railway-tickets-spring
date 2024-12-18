package kpp.lab.railwaytickets.validation;

import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;

import java.util.*;
import java.util.stream.Collectors;

public class StartupPropertiesValidator {

    private StartupPropertiesValidator() {
    }

    private static final Set<String> violations = new HashSet<>();

    public static Set<String> validate(BaseStartupProperties properties) {

        int height = properties.getStationHeight();
        int width = properties.getStationWidth();
        List<BasePosition> deskPositions = properties.getDeskPositions();
        var reservedCashDesk = properties.getReserveDeskPosition();
        deskPositions.add(reservedCashDesk);
        List<BasePosition> entrancePositions = properties.getEntrancePositions();
        int minServiceTime = properties.getMinServiceTime();
        int maxServiceTime = properties.getMaxServiceTime();
        int maxClientsNumber = properties.getMaxClientNumber();

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
                                        List<BasePosition> deskPositions,
                                        List<BasePosition> entrancePositions) {
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
                e.getX() > width || e.getY() != height)) {
            violations.add("Invalid entrance position found");
        }

        if (areAtLeastTwoObjectsInTheSameRow(entrancePositions)) {
            violations.add("Entrance positions can't be in on column");
        }

        if (areAtLeastTwoObjectsInTheSameRow(deskPositions)) {
            violations.add("CashDesks can't be in one column");
        }

    }

    private static boolean areAtLeastTwoObjectsInTheSameRow(List<BasePosition> positions) {
        return positions.stream()
                .map(BasePosition::getX)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count > 1);
    }
}
