package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public class ClientSoldierDecorator extends ClientDecorator {
    public ClientSoldierDecorator(BaseClient client) {
        super(client);
    }


    @Override
    public int calculatePriority() {
        return 3;
    }
}
