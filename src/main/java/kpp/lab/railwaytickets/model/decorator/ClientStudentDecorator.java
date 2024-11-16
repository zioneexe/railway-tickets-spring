package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class ClientStudentDecorator extends ClientDecorator{

    public ClientStudentDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 2 * getClient().calculatePriority();
    }
}
