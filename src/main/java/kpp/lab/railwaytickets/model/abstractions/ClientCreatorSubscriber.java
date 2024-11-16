package kpp.lab.railwaytickets.model.abstractions;

public interface ClientCreatorSubscriber {

    String onClientCreated(BaseClient client);
}
