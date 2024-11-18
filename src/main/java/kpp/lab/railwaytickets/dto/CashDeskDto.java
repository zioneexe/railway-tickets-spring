package kpp.lab.railwaytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CashDeskDto {
    private final int id;
    private final PositionDto position;
    private final List<ClientDto> clientsQueue;
    private final boolean isBackup;
    private final boolean isBroken;
}

