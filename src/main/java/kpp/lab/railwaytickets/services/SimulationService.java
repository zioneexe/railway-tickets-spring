package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.BaseTrainStation;

public interface SimulationService {

    void startClientsGeneration();

    BaseTrainStation getTrainStation();
}
