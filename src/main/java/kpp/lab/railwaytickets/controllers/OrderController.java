package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.dto.TrainStationDto;
import kpp.lab.railwaytickets.mappers.TrainStationMapper;
import kpp.lab.railwaytickets.model.interfaces.BaseLogger;
import kpp.lab.railwaytickets.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private BaseLogger<CashDeskLogDto> cashDeskLogger;

    @Autowired
    public OrderController(BaseLogger<CashDeskLogDto> cashDeskLogger) {
        this.cashDeskLogger = cashDeskLogger;
    }

    @GetMapping("/log")
    public ResponseEntity<List<CashDeskLogDto>> get() {
        var logs = cashDeskLogger.readAll();

        return ResponseEntity.status(HttpStatus.CREATED).body(logs);
    }
}
