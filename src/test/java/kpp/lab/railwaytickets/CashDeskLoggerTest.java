package kpp.lab.railwaytickets;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.services.implementations.CashDeskLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CashDeskLoggerTest {

    private CashDeskLogger cashDeskLogger;

    private static final String LOG_FILE_PATH = "src/test/resources/cashdesk-log.json";

    @BeforeEach
    public void setUp() throws IOException {
        cashDeskLogger = new CashDeskLogger(LOG_FILE_PATH);
        Files.write(Paths.get(LOG_FILE_PATH), "[]".getBytes());
    }

    @Test
    void writeLog_NewLog_AppendsToLogFile() throws IOException {
        // Arrange
        CashDeskLogDto log = new CashDeskLogDto(1, 101, 2, 1000, 2000);

        // Act
        cashDeskLogger.write(log);

        // Assert
        List<CashDeskLogDto> logs = cashDeskLogger.readAll();
        assertEquals(1, logs.size());
        assertEquals(1, logs.get(0).getClientId());
        assertEquals(101, logs.get(0).getCashDeskId());
        assertEquals(2, logs.get(0).getTicketsCount());
        assertEquals(1000, logs.get(0).getStartTimeMs());
        assertEquals(2000, logs.get(0).getEndTimeMs());
    }

    @Test
    void writeLog_MultipleLogs_AppendsCorrectly() throws IOException {
        // Arrange
        CashDeskLogDto log1 = new CashDeskLogDto(1, 101, 2, 1000, 2000);
        CashDeskLogDto log2 = new CashDeskLogDto(2, 102, 4, 1500, 2500);

        // Act
        cashDeskLogger.write(log1);
        cashDeskLogger.write(log2);

        // Assert
        List<CashDeskLogDto> logs = cashDeskLogger.readAll();
        assertEquals(2, logs.size());

        // First log
        assertEquals(1, logs.get(0).getClientId());
        assertEquals(101, logs.get(0).getCashDeskId());
        assertEquals(2, logs.get(0).getTicketsCount());
        assertEquals(1000, logs.get(0).getStartTimeMs());
        assertEquals(2000, logs.get(0).getEndTimeMs());

        // Second log
        assertEquals(2, logs.get(1).getClientId());
        assertEquals(102, logs.get(1).getCashDeskId());
        assertEquals(4, logs.get(1).getTicketsCount());
        assertEquals(1500, logs.get(1).getStartTimeMs());
        assertEquals(2500, logs.get(1).getEndTimeMs());
    }

    @Test
    void readLogs_NonEmptyFile_ReturnsCorrectLogs() throws IOException {
        // Arrange
        CashDeskLogDto log1 = new CashDeskLogDto(1, 101, 2, 1000, 2000);
        CashDeskLogDto log2 = new CashDeskLogDto(2, 102, 4, 1500, 2500);

        // Записуємо два лог-записи в файл
        cashDeskLogger.write(log1);
        cashDeskLogger.write(log2);

        // Act
        List<CashDeskLogDto> logs = cashDeskLogger.readAll();

        // Assert
        assertFalse(logs.isEmpty()); // Перевіряємо, що список не порожній
        assertEquals(2, logs.size()); // Перевіряємо, що кількість елементів правильна

        // Перевірка першого запису
        assertEquals(1, logs.get(0).getClientId());
        assertEquals(101, logs.get(0).getCashDeskId());
        assertEquals(2, logs.get(0).getTicketsCount());
        assertEquals(1000, logs.get(0).getStartTimeMs());
        assertEquals(2000, logs.get(0).getEndTimeMs());

        // Перевірка другого запису
        assertEquals(2, logs.get(1).getClientId());
        assertEquals(102, logs.get(1).getCashDeskId());
        assertEquals(4, logs.get(1).getTicketsCount());
        assertEquals(1500, logs.get(1).getStartTimeMs());
        assertEquals(2500, logs.get(1).getEndTimeMs());
    }


    @Test
    void readLogs_EmptyFile_ReturnsEmptyList() throws IOException {
        // Arrange
        Files.write(Paths.get(LOG_FILE_PATH), "[]".getBytes());

        // Act
        List<CashDeskLogDto> logs = cashDeskLogger.readAll();

        // Assert
        assertTrue(logs.isEmpty());
    }
}
