package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.model.builder.BaseBuilder;
import kpp.lab.railwaytickets.model.builder.BaseDirector;
import kpp.lab.railwaytickets.services.interfaces.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private BaseStartupProperties startupProperties;
    private BaseTrainStation trainStation;
    private BaseBuilder builder;
    private BaseDirector director;

    @Autowired
    public SimulationServiceImpl(BaseDirector director, BaseBuilder builder, BaseStartupProperties startupProperties, BaseTrainStation trainStation) {
        this.startupProperties = startupProperties;
        this.builder = builder;
        this.director = director;
        this.trainStation = trainStation;
    }

    @Override
    public Result getResult() {
        return new Result(
                startupProperties.getStationWidth(),
                startupProperties.getStationHeight(),
                0,
                0
        );
    }

    @Override
    public BaseTrainStation getTrainStation() {
        return trainStation;
    }

    @Override
    public BaseTrainStation createTrainStation() {

        // Build with builder
        director.createTrainStation(builder);
        var buildedTrainStation = builder.getResult();

        // Get properties
        var cashDesks = trainStation.getCashDesks();
        var entrances = trainStation.getEntrances();

        // Clear old
        cashDesks.clear();
        entrances.clear();

        // Set new
        for(var buildedTrainStationDesk :  buildedTrainStation.getCashDesks()) {
            cashDesks.add(buildedTrainStationDesk);
        }

        for(var buildedTrainStationEntrance : buildedTrainStation.getEntrances()) {
            entrances.add(buildedTrainStationEntrance);
        }

        trainStation.addMap(buildedTrainStation.getMap().getSizeX(), buildedTrainStation.getMap().getSizeY());
        trainStation.setMaxClientNumber(buildedTrainStation.getMaxPeopleCount());

        return trainStation;
    }
}
