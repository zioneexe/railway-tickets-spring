package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;

public interface SimulationService {

    void startClientsGeneration();

    void startSimulation();

    void stopSimulation();

    Result getResult();

    public BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
