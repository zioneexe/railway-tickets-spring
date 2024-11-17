package kpp.lab.railwaytickets.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.messages.*;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import kpp.lab.railwaytickets.services.interfaces.SimulationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SocketIOStarter {

    private final SocketIOServer socketServer;

    private static final String START_SIMULATION_EVENT = "start_simulation";
    private static final String STOP_SIMULATION_EVENT = "stop_simulation";
    private static final String NEW_CLIENT_GENERATED_EVENT = "new_client";

    private ClientCreatorService clientCreatorService;

    @Autowired
    public SocketIOStarter(SocketIOServer socketServer, ClientCreatorService clientCreatorService) {
        this.socketServer = socketServer;
        initializeEventListeners();

        this.clientCreatorService = clientCreatorService;
    }

    private void initializeEventListeners() {
        socketServer.addConnectListener(onUserConnect());
        socketServer.addDisconnectListener(onUserDisconnect());
        socketServer.addEventListener(START_SIMULATION_EVENT, SocketRequestMessage.class, onMessageReceived());
    }

    private ConnectListener onUserConnect() {
        return client -> {
            log.info("Client connected - ID: {}", client.getSessionId().toString());
        };
    }

    private DisconnectListener onUserDisconnect() {
        return client -> {
            log.info("Client disconnected - ID: {}", client.getSessionId().toString());
        };
    }

    private DataListener<SocketRequestMessage> onMessageReceived() {
        return (client, message, ackRequest) -> {
            try {
                log.info("Received start message from client: {}", client.getSessionId());
                startSendingDataToClient(client);
            } catch (Exception e) {
                log.error("Error processing message: {}", e.getMessage());
            }
        };
    }

    private void startSendingDataToClient(SocketIOClient client) {
        try {
            while (true) {
                if (client.isChannelOpen()) {
                    client.sendEvent(NEW_CLIENT_GENERATED_EVENT, generateClient());
                }
            }
        } catch (Exception e) {
            log.error("Error sending data to client {}: {}", client.getSessionId(), e.getMessage());
        }
    }

    private SocketResponseMessage generateClient() {
        try {
            return SocketResponseMessage
                    .builder()
                    .client(clientCreatorService.createClient())
                    .build();
        } catch (InterruptedException e) {
            log.error("Error while creating client: {}", e.getMessage());
            return SocketResponseMessage
                    .builder()
                    .client(null)
                    .build();
        }
    }
}