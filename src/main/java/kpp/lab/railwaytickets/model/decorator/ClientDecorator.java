package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public abstract class ClientDecorator implements BaseClient {

    private BaseClient client;

    protected ClientDecorator(BaseClient client) {
        this.client = client;
    }

    protected BaseClient getClient() {
        return client;
    }

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
        client.setPosition(position);
    }
}
