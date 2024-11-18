package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;

public interface SimulationService {

    Result getResult();

    BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
