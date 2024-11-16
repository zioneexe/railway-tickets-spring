package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;

import java.util.UUID;

public class ClientWithChildDecorator extends ClientDecorator{
    protected ClientWithChildDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 2 + getClient().calculatePriority();
    }
}
