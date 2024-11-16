package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;

public interface ClientCreatorSubscriber {

    String onClientCreated(BaseClient client);
}
