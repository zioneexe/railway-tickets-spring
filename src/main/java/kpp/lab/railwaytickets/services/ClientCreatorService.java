package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.ClientCreatorSubscriber;


public interface ClientCreatorService {

     BaseClient createClient();

     void addSubscriber(ClientCreatorSubscriber sub);

     void removeSubscriber(ClientCreatorSubscriber sub);

     void notifySubscribersClientCreated();
}
