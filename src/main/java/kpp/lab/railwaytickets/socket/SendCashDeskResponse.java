package kpp.lab.railwaytickets.socket;

import kpp.lab.railwaytickets.dto.CashDeskDto;

@FunctionalInterface
public interface SendCashDeskResponse {
    void execute(CashDeskDto cashDeskDto);
}