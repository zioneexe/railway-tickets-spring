package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;


public interface ClientCreatorService {

     BaseClient createClient();

     void addSubscriber(ClientCreatorSubscriber sub);

     void removeSubscriber(ClientCreatorSubscriber sub);

     void notifySubscribersClientCreated();
}
