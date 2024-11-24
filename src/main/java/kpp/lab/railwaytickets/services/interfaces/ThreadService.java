package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.socket.SendCashDeskResponse;
import kpp.lab.railwaytickets.socket.SendCreatedClientResponse;

public interface ThreadService {

    void startClientGeneration(SendCreatedClientResponse responseFunction);

    void stopClientGeneration();

    void startCashDesks(SendCashDeskResponse sendCashDeskResponse, long applicationStartTime);

    void stopCashDesks();

}
