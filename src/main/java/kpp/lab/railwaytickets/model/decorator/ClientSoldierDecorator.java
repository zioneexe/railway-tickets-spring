package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;

import java.util.UUID;

public class ClientSoldierDecorator extends ClientDecorator {
    public ClientSoldierDecorator(BaseClient client) {
        super(client);
        this.decoratedClient = client;
    }


    @Override
    public int calculatePriority() {
        return 3 + getClient().calculatePriority();
    }
}
