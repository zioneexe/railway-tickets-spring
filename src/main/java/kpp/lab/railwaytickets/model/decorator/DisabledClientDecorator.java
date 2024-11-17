package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public class DisabledClientDecorator extends ClientDecorator{
    public DisabledClientDecorator(BaseClient client) {
        super(client);
    }

    public BaseClient baseClient;
    @Override
    public int calculatePriority() {
        return baseClient.calculatePriority() + 3;
    }
}
