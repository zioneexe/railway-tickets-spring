package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public class ClientStudentDecorator extends ClientDecorator{

    public ClientStudentDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 3 + getClient().calculatePriority();
    }

    public String getType() {
        return getClient().getType() + "_student";
    }
}
