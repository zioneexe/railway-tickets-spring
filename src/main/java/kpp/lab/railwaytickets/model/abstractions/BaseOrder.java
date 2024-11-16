package kpp.lab.railwaytickets.model.abstractions;

import java.time.LocalDateTime;

public interface BaseOrder {

    int getTicketsCount();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    BaseClient getClient();

    BaseCashDesk getCashDesk();

}
