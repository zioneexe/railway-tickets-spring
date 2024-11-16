package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseStartupProperties;
import kpp.lab.railwaytickets.model.abstractions.BaseTrainStation;
import kpp.lab.railwaytickets.model.builder.BaseBuilder;
import kpp.lab.railwaytickets.model.builder.BaseDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private BaseStartupProperties startupProperties;

    private BaseTrainStation trainStation;
    private BaseBuilder builder;
    private BaseDirector director;

    private double renewGenerationCoeff = 0.7;

    @Autowired
    public SimulationServiceImpl(BaseDirector director, BaseBuilder builder, BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
        this.builder = builder;
        this.director = director;
    }

    @Override
    public void startSimulation() {
        startClientsGeneration();

    }

    @Override
    public void stopSimulation() {

    }

    @Override
    public void startClientsGeneration() {

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
