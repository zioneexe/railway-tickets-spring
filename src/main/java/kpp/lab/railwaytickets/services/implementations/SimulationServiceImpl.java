package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.model.builder.BaseBuilder;
import kpp.lab.railwaytickets.model.builder.BaseDirector;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
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
    public SimulationServiceImpl(BaseDirector director, BaseBuilder builder, BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
        this.builder = builder;
        this.director = director;
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
        director.createTrainStation(builder);
        trainStation = builder.getResult();

        return trainStation;
    }
}