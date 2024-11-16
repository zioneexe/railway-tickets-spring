package kpp.lab.railwaytickets.services.Base;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.abstractions.BaseTrainStation;

public interface SimulationService {

    void startSimulation();

    void stopSimulation();

    Result getResult();

    BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
