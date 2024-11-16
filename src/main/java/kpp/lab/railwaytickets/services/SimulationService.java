package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseTrainStation;

public interface SimulationService {

    void startClientsGeneration();

    public BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
