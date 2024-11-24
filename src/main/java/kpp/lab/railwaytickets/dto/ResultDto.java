package kpp.lab.railwaytickets.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResultDto {

    private final int totalClients;
    private final int totalTickets;
}
