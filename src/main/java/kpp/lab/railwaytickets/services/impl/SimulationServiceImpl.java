package kpp.lab.railwaytickets.services.impl;

import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.abstractions.BaseStartupProperties;
import kpp.lab.railwaytickets.model.abstractions.BaseTrainStation;
import kpp.lab.railwaytickets.model.builder.BaseBuilder;
import kpp.lab.railwaytickets.model.builder.BaseDirector;
import kpp.lab.railwaytickets.services.ClientCashDeskService;
import kpp.lab.railwaytickets.services.ClientCreatorService;
import kpp.lab.railwaytickets.services.SimulationService;
import kpp.lab.railwaytickets.services.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private BaseStartupProperties startupProperties;

    private ThreadService threadService;

    private BaseTrainStation trainStation;
    private BaseBuilder builder;
    private BaseDirector director;
    private ClientCreatorService clientCreatorService;
    private ClientCashDeskService clientCashDeskService;

    private double renewGenerationCoeff = 0.7;

    @Autowired
    public SimulationServiceImpl(BaseDirector director, BaseBuilder builder, BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
        this.builder = builder;
        this.director = director;
        this.clientCreatorService = new ClientCreatorServiceImpl(startupProperties.getClientGenerator());
        //this.clientCashDeskService = new ClientCashDeskServiceImpl();
        this.trainStation = getTrainStation();
    }

    @Override
    public void startSimulation() {
        threadService = new ThreadServiceImpl(clientCreatorService, clientCashDeskService);

        //threadService.startCashDesks();
        //threadService.startClientGenerator();
    }

    @Override
    public void stopSimulation() {
        threadService.shutdownAll();
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
