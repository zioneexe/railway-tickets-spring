package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class DisabledClientDecorator extends ClientDecorator{
    public DisabledClientDecorator(BaseClient client) {
        super(client);
    }

    public BaseClient baseClient;
    @Override
    public int calculatePriority() {
        return 3 * getClient().calculatePriority();
    }
}
