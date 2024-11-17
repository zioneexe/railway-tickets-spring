package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public class ClientWithChildDecorator extends ClientDecorator{
    public ClientWithChildDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 2;
    }
}
