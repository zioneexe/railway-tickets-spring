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

    }

    public String startSimulation() {

    }

    public String stopSimulation() {

    }

    public String updateView() {

    }

    @Override
    public String onClientCreated(BaseClient client) {
        return "";
    }
}
