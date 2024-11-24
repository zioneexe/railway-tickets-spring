package kpp.lab.railwaytickets.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

public class ConfigFileGetter {

    private ConfigFileGetter() {}

    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File configFile = new File("src/main/resources/config.json");
            rootNode = objectMapper.readTree(configFile);
        } catch (IOException e) {
            LOGGER.error("Error reading configuration file: {}", e.getMessage());
        }
    }

    public static <T> T get(String key, Class<T> type) {
        if (rootNode == null) {
            LOGGER.error("Configuration file loading issue.");
            throw new IllegalStateException("Configuration file not loaded.");
        }

        JsonNode node = rootNode;
        String[] keys = key.split("\\.");

        for (String k : keys) {
            node = node.get(k);
            if (node == null) {
                LOGGER.error("Configuration key not found in the configuration {}.", key);
                throw new IllegalArgumentException("Key '" + key + "' not found in configuration.");
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.treeToValue(node, type);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert value for key '" + key + "' to "
                    + type.getSimpleName(), e);
        }
    }
}