package kpp.lab.railwaytickets.socket;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

@FunctionalInterface
public interface SendCreatedClientResponse {
    void execute(BaseClient generatedClient);
}