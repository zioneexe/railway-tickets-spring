package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class Client implements BaseClient {

    private final int id;

    private BasePosition position;

    private final int ticketNumber;

    public Client(int id, BasePosition position, int ticketNumber) {
        this.id = id;
        this.position = position;
        this.ticketNumber = ticketNumber;
    }

    @Override
    public int calculatePriority() {
        return 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public BasePosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(BasePosition position) {
        this.position = position;
    }

}
