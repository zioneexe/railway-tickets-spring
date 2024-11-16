package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseTrainStation;

public interface SimulationService {

    void startSimulation();

    void stopSimulation();

    void startClientsGeneration();

    public BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
