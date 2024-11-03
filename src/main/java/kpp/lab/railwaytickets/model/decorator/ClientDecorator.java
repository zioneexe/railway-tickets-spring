package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;

public abstract class ClientDecorator implements BaseClient {

    private BaseClient client;

    protected ClientDecorator(BaseClient client) {

    }
}
