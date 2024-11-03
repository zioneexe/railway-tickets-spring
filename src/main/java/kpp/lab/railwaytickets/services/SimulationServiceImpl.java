package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.BaseStartupProperties;
import kpp.lab.railwaytickets.model.BaseTrainStation;
import kpp.lab.railwaytickets.model.builder.BaseDirector;

public class SimulationServiceImpl implements SimulationService {

    private double renewGenerationCoeff;
    private BaseTrainStation trainStation;
    private BaseStartupProperties startupProperties;

    public SimulationServiceImpl(BaseDirector director, double renewGenerationCoeff, BaseStartupProperties startupProperties) {
        this.renewGenerationCoeff = renewGenerationCoeff;
        this.startupProperties = startupProperties;
        // Assuming BaseDirector helps initialize or set up the train station
       // this.trainStation = director.getTrainStation();
    }

    @Override
    public void startClientsGeneration() {
    }

    @Override
    public BaseTrainStation getTrainStation() {
        return trainStation;
    }
}
