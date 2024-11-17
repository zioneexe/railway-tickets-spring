package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

public abstract class ClientDecorator implements BaseClient {

    private final BaseClient client;

    protected ClientDecorator(BaseClient client) {
        this.client = client;
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
