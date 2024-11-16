package kpp.lab.railwaytickets.model.decorator;

import com.fasterxml.jackson.databind.ser.Serializers;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

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

    public BaseClient getClient() {
        return client;
    }
}
