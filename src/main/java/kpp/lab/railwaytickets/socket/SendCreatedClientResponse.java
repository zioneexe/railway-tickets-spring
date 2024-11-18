package kpp.lab.railwaytickets.socket;

import kpp.lab.railwaytickets.dto.ClientDto;

@FunctionalInterface
public interface SendCreatedClientResponse {
    void execute(ClientDto generatedClient);
}