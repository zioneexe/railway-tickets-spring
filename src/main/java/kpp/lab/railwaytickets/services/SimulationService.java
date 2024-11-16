package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.abstractions.BaseTrainStation;

public interface SimulationService {

    void startSimulation();

    void stopSimulation();

    Result getResult();

    public BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
