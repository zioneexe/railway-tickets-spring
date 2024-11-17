package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.HashMap;
import java.util.List;

public interface BaseGeneratorHelper {
    BaseClient decorateClient(BaseClient client);
    BaseClient createClient(List<BasePosition> entrancePositions);
    void waitFor(int timeMs) throws InterruptedException;
    int getRandomBetween(int min, int max);
}
