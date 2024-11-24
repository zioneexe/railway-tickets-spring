package kpp.lab.railwaytickets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.dto.ResultDto;
import kpp.lab.railwaytickets.mappers.ResultMapper;
import kpp.lab.railwaytickets.model.Result;
import kpp.lab.railwaytickets.model.interfaces.BaseLogger;

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

    @GetMapping("/result")
    public ResponseEntity<ResultDto> getLogsForResult() {
        var logs = cashDeskLogger.readAll();

        int totalTickets = logs.stream()
            .mapToInt(CashDeskLogDto::getTicketsCount)
            .sum();
            
        int totalPassengers = (int) logs.stream()
            .map(CashDeskLogDto::getClientId)
            .distinct()
            .count();

        Result result = new Result(totalPassengers, totalTickets);

        return ResponseEntity.status(HttpStatus.CREATED).body(ResultMapper.resultToDto(result));
    }
}
