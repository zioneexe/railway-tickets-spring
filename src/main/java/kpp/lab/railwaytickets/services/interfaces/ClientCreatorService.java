package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;


public interface ClientCreatorService {

    BaseClient createClient() throws InterruptedException;
}
