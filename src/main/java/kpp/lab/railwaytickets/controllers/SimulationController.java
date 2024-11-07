package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BaseStartupProperties;
import kpp.lab.railwaytickets.model.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.services.ClientCashDeskService;
import kpp.lab.railwaytickets.services.ClientCreatorService;
import kpp.lab.railwaytickets.services.SimulationService;

public class SimulationController implements ClientCreatorSubscriber {

    private SimulationService simulationService;

    private ClientCashDeskService clientCashDeskService;

    private ClientCreatorService clientCreatorService;

    private BaseStartupProperties startupProperties;

    public SimulationController(SimulationService simulationService) {

    }

    public String generateMap() {
        return null;
    }

    public String startSimulation() {
        return null;
    }

    public String stopSimulation() {
        return null;
    }

    public String updateView() {
        return null;
    }

    @Override
    public String onClientCreated(BaseClient client) {
        return "";
    }
}
