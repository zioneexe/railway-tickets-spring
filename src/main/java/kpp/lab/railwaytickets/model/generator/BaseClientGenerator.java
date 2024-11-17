package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public interface BaseClientGenerator {
    BaseClient generateClient() throws InterruptedException;
}
