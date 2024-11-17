package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.socket.SendCreatedClientResponse;

import java.util.List;

public interface ThreadService {

    void startClientGeneration(SendCreatedClientResponse responseLambda);

    void stopClientGenerator();

    void startCashDesks(List<BaseCashDesk> cashDesks);

    void stopCashDesks();

    void shutdownAll();

}
