package kpp.lab.railwaytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClientDto {

    private final int id;

    private final PositionDto position;

    private final int ticketNumber;

    private final int priority;

    private final String type;
}

