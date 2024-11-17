package kpp.lab.railwaytickets.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import kpp.lab.railwaytickets.dto.ClientDto;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.messages.*;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import kpp.lab.railwaytickets.services.interfaces.ThreadService;
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

    private ThreadService threadService;

    @Autowired
    public SocketIOStarter(SocketIOServer socketServer, ThreadService threadService) {
        this.socketServer = socketServer;
        initializeEventListeners();

        this.threadService = threadService;
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
                log.info("Received << " + START_SIMULATION_EVENT + " >> from client: {}", client.getSessionId());

                threadService.startClientGeneration((ClientDto generatedClient) -> {
                    if(client.isChannelOpen()) {
                        client.sendEvent(NEW_CLIENT_GENERATED_EVENT, generatedClient);
                    }
                });

            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }
        };
    }
}