package kpp.lab.railwaytickets.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CashDeskLogDto {
    private int clientId;

    private int cashDeskId;

    private int ticketsCount;

    private long startTimeMs;

    private long endTimeMs;
}
