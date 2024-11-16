package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class DisabledClientDecorator extends ClientDecorator{
    protected DisabledClientDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 3 * getClient().calculatePriority();
    }
}
