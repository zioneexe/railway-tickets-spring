package kpp.lab.railwaytickets.services.Base;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

import java.util.concurrent.TimeUnit;


public interface ClientCreatorService {

     /*
     BaseClient createClient();

     void addSubscriber(ClientCreatorSubscriber sub);

     void removeSubscriber(ClientCreatorSubscriber sub);

     void notifySubscribersClientCreated(BaseClient client);

     BaseClientGenerator getClientGenerator();

      */

     public void startGenerating();
     public void shutdown();
}
