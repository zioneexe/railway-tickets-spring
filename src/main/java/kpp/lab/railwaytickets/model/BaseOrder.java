package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;

import java.time.LocalDateTime;

public interface BaseOrder {

    int getTicketsCount();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    BaseClient getClient();

    BaseCashDesk getCashDesk();

}
