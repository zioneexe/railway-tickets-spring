package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.TrainStationDto;
import kpp.lab.railwaytickets.mappers.TrainStationMapper;
import kpp.lab.railwaytickets.services.interfaces.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/trainStation")
    public ResponseEntity<TrainStationDto> createTrainStation() {
        var trainStation = simulationService.createTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }

    @GetMapping("/trainStation")
    public ResponseEntity<TrainStationDto> getTrainStation() {
        var trainStation = simulationService.getTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }
}
