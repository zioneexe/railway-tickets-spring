package kpp.lab.railwaytickets.services;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.ClientCreatorSubscriber;

import java.util.concurrent.TimeUnit;


public interface ClientCreatorService {

     /*
     BaseClient createClient();

     void addSubscriber(ClientCreatorSubscriber sub);

     void removeSubscriber(ClientCreatorSubscriber sub);

     void notifySubscribersClientCreated();

      */

     public void startGenerating();
     public void shutdown();
}
