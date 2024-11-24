package kpp.lab.railwaytickets.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.builder.BaseBuilder;
import kpp.lab.railwaytickets.model.builder.BaseDirector;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.services.interfaces.SimulationService;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final BaseStartupProperties startupProperties;
    private final BaseTrainStation trainStation;
    private final BaseBuilder builder;
    private final BaseDirector director;

    @Autowired
    public SimulationServiceImpl(BaseDirector director, BaseBuilder builder,
                                 BaseStartupProperties startupProperties, BaseTrainStation trainStation) {
        this.startupProperties = startupProperties;
        this.builder = builder;
        this.director = director;
        this.trainStation = trainStation;
    }

    @Override
    public Result getResult(List<CashDeskLogDto> logs) {
        
        int totalTickets = logs.stream()
            .mapToInt(CashDeskLogDto::getTicketsCount)
            .sum();
            
        int totalPassengers = (int) logs.stream()
            .map(CashDeskLogDto::getClientId)
            .distinct()
            .count();

        return new Result(totalPassengers, totalTickets);
    }

    @Override
    public BaseTrainStation getTrainStation() {
        return trainStation;
    }

    @Override
    public BaseTrainStation createTrainStation() {

        director.createTrainStation(builder);
        var builtTrainStation = builder.getResult();

        var cashDesks = trainStation.getCashDesks();
        var entrances = trainStation.getEntrances();

        cashDesks.clear();
        entrances.clear();

        cashDesks.addAll(builtTrainStation.getCashDesks());
        entrances.addAll(builtTrainStation.getEntrances());

        trainStation.addMap(builtTrainStation.getMap().getSizeX(), builtTrainStation.getMap().getSizeY());
        trainStation.setMaxClientNumber(builtTrainStation.getMaxPeopleCount());

        return trainStation;
    }
}
