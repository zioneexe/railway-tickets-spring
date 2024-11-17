package kpp.lab.railwaytickets.socket;

import kpp.lab.railwaytickets.dto.ClientDto;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

@FunctionalInterface
public interface SendCreatedClientResponse {
    void execute(ClientDto generatedClient);
}