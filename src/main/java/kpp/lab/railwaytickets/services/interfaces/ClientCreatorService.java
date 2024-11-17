package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.ClientCreatorSubscriber;


public interface ClientCreatorService {

     BaseClient createClient() throws InterruptedException;
}
