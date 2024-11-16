package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class ClientWithChildDecorator extends ClientDecorator{
    protected ClientWithChildDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 2;
    }
}
