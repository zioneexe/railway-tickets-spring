package kpp.lab.railwaytickets.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.model.interfaces.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

@Service
public class CashDeskLoggerServiceImpl implements BaseLogger<CashDeskLogDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path logFilePath;

    public static final int EMPTY_ARR_LENGTH = 2;
    public static final String BRACKETS = "[]";
    public static final String SEPARATOR = ",";
    public static final String CLOSING_BRACKET = "]";
    public static final String FILE_ACCESS_MODE = "rw";

    @Autowired
    public CashDeskLoggerServiceImpl(@Value("${log.filename}") String logFileName) {
        this.logFilePath = Paths.get(logFileName);

        if (Files.notExists(logFilePath)) {
            try {
                Files.write(logFilePath, BRACKETS.getBytes());
            } catch (IOException e) {
                LOGGER.error("Error when opening file: {}", e.getMessage());
            }
        }
    }

    @Override
    public synchronized void write(CashDeskLogDto order) {
        try (RandomAccessFile file = new RandomAccessFile(logFilePath.toFile(), FILE_ACCESS_MODE)) {
            long fileLength = file.length();

            if (fileLength <= EMPTY_ARR_LENGTH) {
                file.seek(fileLength - 1);
                String json = objectMapper.writeValueAsString(order);
                file.write(json.getBytes());
            } else {
                file.seek(fileLength - 1);
                file.write(SEPARATOR.getBytes());
                String json = objectMapper.writeValueAsString(order);
                file.write(json.getBytes());
            }

            file.write(CLOSING_BRACKET.getBytes());
        } catch (IOException e) {
            LOGGER.error("Error when logging message: {}", e.getMessage());
        }
    }

    @Override
    public synchronized List<CashDeskLogDto> readAll() {
        try {
            return objectMapper.readValue(
                    Files.newInputStream(logFilePath),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CashDeskLogDto.class)
            );
        } catch (IOException e) {
            LOGGER.error("Error reading from log file: {}", e.getMessage());
        }
        return Collections.emptyList();
    }

    @PreDestroy
    public synchronized void clear() {
        try {
            Files.write(logFilePath, BRACKETS.getBytes());
        } catch (IOException e) {
            LOGGER.error("Error when clearing file: {}", e.getMessage());
        }
    }
}
