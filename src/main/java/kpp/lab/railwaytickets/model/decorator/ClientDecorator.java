package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;

public abstract class ClientDecorator implements BaseClient {

    private BaseClient client;

    public ClientDecorator(BaseClient client) {}

    @Override
    public int getId() {
        return client.getId();
    }

    @Override
    public int getTicketNumber() {
        return client.getTicketNumber();
    }

    @Override
    public BasePosition getPosition() {
        return client.getPosition();
    }

    @Override
    public void setPosition(BasePosition position) {
        this.client.setPosition(position);
    }
}
