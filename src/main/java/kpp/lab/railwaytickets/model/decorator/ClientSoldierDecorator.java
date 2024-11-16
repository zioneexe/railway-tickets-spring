package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;

import java.util.UUID;

public class ClientSoldierDecorator extends ClientDecorator {

    private final BaseClient decoratedClient;

    public ClientSoldierDecorator(BaseClient client) {
        super(client);
        this.decoratedClient = client;
    }

    @Override
    public int calculatePriority() {
        return 3 + decoratedClient.calculatePriority();
    }
}
