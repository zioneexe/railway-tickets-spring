package kpp.lab.railwaytickets.model;

import jakarta.annotation.PreDestroy;
import kpp.lab.railwaytickets.model.abstractions.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

@Service
public class Logger implements BaseLogger {

    private BufferedWriter writer;

    @Autowired
    public Logger(@Value("${log.filename}") String logFileName) {

        try {
            this.writer = new BufferedWriter(new FileWriter(logFileName, true));
        } catch (IOException e) {
            LOGGER.error("Error when opening file: {}", e.getMessage());
        }
    }

    @Override
    public void log(String message) {
        try {
            if (writer != null) {
                writer.append(message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            LOGGER.error("Error when logging message: {}", e.getMessage());
        }
    }

    @PreDestroy
    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            LOGGER.error("Error when closing logging stream: {}", e.getMessage());
        }
    }
}
