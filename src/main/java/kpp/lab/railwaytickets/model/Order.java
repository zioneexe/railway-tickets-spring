package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;

import java.time.LocalDateTime;

public class Order implements BaseOrder {

    private int ticketsCount;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BaseClient client;

    private BaseCashDesk cashDesk;

    public Order(int ticketsCount, LocalDateTime startTime, LocalDateTime endTime, BaseClient baseClient, BaseCashDesk baseCashDesk) {
        this.ticketsCount = ticketsCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.client = baseClient;
        this.cashDesk = baseCashDesk;
    }

    @Override
    public int getTicketsCount() {
        return ticketsCount;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public BaseClient getClient() {
        return client;
    }

    @Override
    public BaseCashDesk getCashDesk() {
        return cashDesk;
    }
}
