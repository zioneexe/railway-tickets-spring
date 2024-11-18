package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.socket.SendCashDeskResponse;
import kpp.lab.railwaytickets.socket.SendCreatedClientResponse;

import java.util.List;

public interface ThreadService {

    void startClientGeneration(SendCreatedClientResponse responseLambda);

    void stopClientGeneration();

    void startCashDesks(SendCashDeskResponse sendCashDeskResponse);

    void stopCashDesks();

    void shutdownAll();

}
