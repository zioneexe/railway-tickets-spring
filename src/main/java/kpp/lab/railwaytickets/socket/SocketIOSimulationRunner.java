package kpp.lab.railwaytickets.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import kpp.lab.railwaytickets.dto.CashDeskDto;
import kpp.lab.railwaytickets.dto.ClientDto;
import kpp.lab.railwaytickets.model.messages.*;
import kpp.lab.railwaytickets.services.interfaces.ThreadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class SocketIOSimulationRunner {

    private final SocketIOServer socketServer;

    private static final String START_SIMULATION_EVENT = "start_simulation";
    private static final String STOP_SIMULATION_EVENT = "stop_simulation";
    private static final String NEW_CLIENT_GENERATED_EVENT = "new_client";
    private static final String CASH_DESK_EVENT = "cash_desk";
    private static final String SIMULATION_STOPPED_EVENT = "simulation_stopped";

    private long startSimulationTime;
    private ThreadService threadService;

    @Autowired
    public SocketIOSimulationRunner(SocketIOServer socketServer, ThreadService threadService) {
        this.socketServer = socketServer;
        initializeEventListeners();
        this.threadService = threadService;

        startSimulationTime = Instant.now().toEpochMilli();        startSimulationTime = Instant.now().toEpochMilli();
    }

    private void initializeEventListeners() {
        socketServer.addConnectListener(onUserConnect());
        socketServer.addDisconnectListener(onUserDisconnect());
        socketServer.addEventListener(START_SIMULATION_EVENT, RequestMessage.class, onStartSimulationEventReceived());
        socketServer.addEventListener(STOP_SIMULATION_EVENT, RequestMessage.class, onStopSimulationEventReceived());
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

    private DataListener<RequestMessage> onStartSimulationEventReceived() {
        return (client, message, ackRequest) -> {
            try {
                log.info("Received << " + START_SIMULATION_EVENT + " >> from client: {}", client.getSessionId());

                threadService.startClientGeneration((ClientDto generatedClient) -> {
                    if(client.isChannelOpen()) {
                        client.sendEvent(NEW_CLIENT_GENERATED_EVENT, generatedClient);
                    }
                });

                threadService.startCashDesks((CashDeskDto cashDeskResponse) -> {
                    if(client.isChannelOpen()) {
                        client.sendEvent(CASH_DESK_EVENT, cashDeskResponse);
                    }
                }, startSimulationTime);

            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }
        };
    }

    private DataListener<RequestMessage> onStopSimulationEventReceived() {
        return (client, message, ackRequest) -> {
            try {
                log.info("Received << " + STOP_SIMULATION_EVENT + " >> from client: {}", client.getSessionId());

                threadService.stopClientGeneration();
                threadService.stopCashDesks();
                if(client.isChannelOpen()) {
                    client.sendEvent(SIMULATION_STOPPED_EVENT);
                }

            } catch (Exception e) {
                log.error("Error: {}", e.getMessage());
            }
        };
    }
}