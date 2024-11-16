package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.StartupPropertiesDto;
import kpp.lab.railwaytickets.dto.TrainStationDto;
import kpp.lab.railwaytickets.mappers.TrainStationMapper;
import kpp.lab.railwaytickets.model.StartupProperties;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.services.ClientCashDeskService;
import kpp.lab.railwaytickets.services.ClientCreatorService;
import kpp.lab.railwaytickets.services.ClientCreatorServiceImpl;
import kpp.lab.railwaytickets.services.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
public class SimulationController implements ClientCreatorSubscriber {

    private SimulationService simulationService;

    private ClientCashDeskService clientCashDeskService;

    private ClientCreatorService clientCreatorService;

    //private BaseStartupProperties startupProperties;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
        this.clientCreatorService = new ClientCreatorServiceImpl(StartupProperties.getInstance().getClientGenerator());
    }

    @PostMapping("/create")
    public ResponseEntity<TrainStationDto> createTrainStation() {

        var trainStation = simulationService.createTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation() {
        simulationService.startSimulation();
        return ResponseEntity.ok("Simulation started");
    }

    public String stopSimulation() {
        simulationService.stopSimulation();
        return "Simulation stopped";
    }

    public String updateView() {
        return null;
    }

    @Override
    public String onClientCreated(BaseClient client) {
        return "";
    }
}
