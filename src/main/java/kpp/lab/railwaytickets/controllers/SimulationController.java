package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.ResultDto;
import kpp.lab.railwaytickets.dto.TrainStationDto;
import kpp.lab.railwaytickets.mappers.ResultMapper;
import kpp.lab.railwaytickets.mappers.TrainStationMapper;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.services.Base.ClientCashDeskService;
import kpp.lab.railwaytickets.services.Base.SimulationService;
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

    //private BaseStartupProperties startupProperties;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/create")
    public ResponseEntity<TrainStationDto> createTrainStation() {

        var trainStation = simulationService.createTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation() {
        simulationService.startSimulation();
        // Placeholder
        return ResponseEntity.status(HttpStatus.CREATED).body("Simulation started");
    }

    @PostMapping("/stop")
    public ResponseEntity<ResultDto> stopSimulation() {
        simulationService.stopSimulation();
        return ResponseEntity.status(HttpStatus.OK).body(ResultMapper.resultToDto(simulationService.getResult()));
    }

    public String updateView() {
        return null;
    }

    @Override
    public String onClientCreated(BaseClient client) {
        return "";
    }
}
