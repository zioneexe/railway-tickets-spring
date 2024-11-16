package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;


public interface ClientCreatorService {

     BaseClient createClient();

     void addSubscriber(ClientCreatorSubscriber sub);

     void removeSubscriber(ClientCreatorSubscriber sub);

     void notifySubscribersClientCreated(BaseClient client);

     BaseClientGenerator getClientGenerator();
}
