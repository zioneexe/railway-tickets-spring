package kpp.lab.railwaytickets.services.interfaces;

import java.util.List;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;

public interface SimulationService {

    Result getResult(List<CashDeskLogDto> logs);

    BaseTrainStation createTrainStation();

    BaseTrainStation getTrainStation();
}
