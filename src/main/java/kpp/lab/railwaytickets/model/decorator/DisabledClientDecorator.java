package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public class DisabledClientDecorator extends ClientDecorator{
    public DisabledClientDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 3 + getClient().calculatePriority();
    }

    public String getType() {
        return getClient().getType() + "_disabled";
    }
}
