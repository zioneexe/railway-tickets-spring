package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import java.util.List;

public interface CashDeskLoggerService {

    void write(CashDeskLogDto order); // Method to write a log entry

    List<CashDeskLogDto> readAll(); // Method to read all log entries
}
