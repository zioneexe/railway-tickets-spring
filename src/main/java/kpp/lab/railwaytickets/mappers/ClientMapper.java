package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.ClientDto;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public class ClientMapper {
    public static ClientDto baseClientToClientDto(BaseClient client) {
        return new ClientDto(
                client.getId(),
                PositionMapper.basePositionToPositionDto(client.getPosition()),
                client.getTicketNumber(),
                client.calculatePriority()
        );
    }
}
