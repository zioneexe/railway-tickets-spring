package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.*;
import kpp.lab.railwaytickets.mappers.TrainStationMapper;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import kpp.lab.railwaytickets.services.interfaces.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService, ClientCashDeskService clientCashDeskService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/trainStation")
    public ResponseEntity<TrainStationDto> createTrainStation() {

        var trainStation = simulationService.createTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }

    @GetMapping("/trainStation")
    public ResponseEntity<TrainStationDto> getTrainStation() {

        var trainStation = simulationService.getTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }
}
