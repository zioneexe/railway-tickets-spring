package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.CashDeskDto;
import kpp.lab.railwaytickets.dto.ClientDto;
import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;

import java.util.ArrayList;
import java.util.List;

public class CashDeskMapper {

    private CashDeskMapper() {}

    public static CashDeskDto baseCashDeskToCashDeskDto(BaseCashDesk cashDesk) {
        List<ClientDto> clientsDtoQueue = new ArrayList<>();
        for (var client : cashDesk.getQueue()) {
            clientsDtoQueue.add(ClientMapper.baseClientToClientDto(client));
        }

        return new CashDeskDto(
                cashDesk.getId(),
                PositionMapper.basePositionToPositionDto(cashDesk.getPosition()),
                clientsDtoQueue,
                cashDesk.getIsBackup(),
                cashDesk.getIsBroken()
        );
    }
}
