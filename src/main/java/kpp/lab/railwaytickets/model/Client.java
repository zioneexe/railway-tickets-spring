package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

public class Client implements BaseClient {

    private static int nextId = 1;

    private final int id;
    private BasePosition position;
    private final int ticketNumber;

    public Client(int id, BasePosition position, int ticketNumber) {
        this.id = id;
        this.position = position;
        this.ticketNumber = ticketNumber;
    }

    public Client(BasePosition position, int ticketNumber) {
        this.ticketNumber = ticketNumber;
        this.position = position;
        this.id = nextId++;
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

    @Override
    public int calculatePriority() {
        return 0;
    }

    public String getType() {
        return "Client";
    }

}
