package kpp.lab.railwaytickets.model;

import java.util.UUID;

public class Client implements BaseClient {

    private int id;

    private BasePosition position;

    private int ticketNumber;

    public Client(BasePosition position, int ticketNumber) {
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
