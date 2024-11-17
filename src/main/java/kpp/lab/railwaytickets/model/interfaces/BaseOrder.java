package kpp.lab.railwaytickets.model.interfaces;

import java.time.LocalDateTime;

public interface BaseOrder {

    int getTicketsCount();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    BaseClient getClient();

    BaseCashDesk getCashDesk();

}
