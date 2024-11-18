package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.TrainStationDto;
import kpp.lab.railwaytickets.mappers.TrainStationMapper;
import kpp.lab.railwaytickets.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String showOrdersLog() {
        return null;
    }

    @GetMapping("/log")
    public ResponseEntity<TrainStationDto> createTrainStation() {

        var trainStation = simulationService.createTrainStation();

        return ResponseEntity.status(HttpStatus.CREATED).body(TrainStationMapper.baseTrainStationToTrainStationDto(trainStation));
    }
}
