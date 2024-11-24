package kpp.lab.railwaytickets.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import kpp.lab.railwaytickets.dto.CashDeskDto;
import kpp.lab.railwaytickets.dto.ClientDto;
import kpp.lab.railwaytickets.model.messages.RequestMessage;
import kpp.lab.railwaytickets.services.interfaces.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

@Service
public class SocketIOSimulationRunner {

    private final SocketIOServer socketServer;

    private static final String START_SIMULATION_EVENT = "start_simulation";
    private static final String STOP_SIMULATION_EVENT = "stop_simulation";
    private static final String NEW_CLIENT_GENERATED_EVENT = "new_client";
    private static final String CASH_DESK_EVENT = "cash_desk";
    private static final String SIMULATION_STOPPED_EVENT = "simulation_stopped";

    private final long startSimulationTime;
    private final ThreadService threadService;

    @Autowired
    public SocketIOSimulationRunner(SocketIOServer socketServer, ThreadService threadService) {
        this.socketServer = socketServer;
        initializeEventListeners();
        this.threadService = threadService;

        startSimulationTime = Instant.now().toEpochMilli();
    }

    private void initializeEventListeners() {
        socketServer.addConnectListener(onUserConnect());
        socketServer.addDisconnectListener(onUserDisconnect());
        socketServer.addEventListener(START_SIMULATION_EVENT, RequestMessage.class, onStartSimulationEventReceived());
        socketServer.addEventListener(STOP_SIMULATION_EVENT, RequestMessage.class, onStopSimulationEventReceived());
    }

    private ConnectListener onUserConnect() {
        return client -> LOGGER.info("Client connected - ID: {}", client.getSessionId());
    }

    private DisconnectListener onUserDisconnect() {
        return client -> LOGGER.info("Client disconnected - ID: {}", client.getSessionId());
    }

    private DataListener<RequestMessage> onStartSimulationEventReceived() {
        return (client, _, _) -> {
            try {
                LOGGER.info("Received << " + START_SIMULATION_EVENT + " >> from client: {}", client.getSessionId());

                threadService.startClientGeneration((ClientDto generatedClient) -> {
                    if (client.isChannelOpen()) {
                        client.sendEvent(NEW_CLIENT_GENERATED_EVENT, generatedClient);
                    }
                });

                threadService.startCashDesks((CashDeskDto cashDeskResponse) -> {
                    if (client.isChannelOpen()) {
                        client.sendEvent(CASH_DESK_EVENT, cashDeskResponse);
                    }
                }, startSimulationTime);

            } catch (Exception e) {
                LOGGER.error("Error when starting simulation: {}", e.getMessage());
            }
        };
    }

    private DataListener<RequestMessage> onStopSimulationEventReceived() {
        return (client, _, _) -> {
            try {
                LOGGER.info("Received << " + STOP_SIMULATION_EVENT + " >> from client: {}", client.getSessionId());

                threadService.stopClientGeneration();
                threadService.stopCashDesks();
                if (client.isChannelOpen()) {
                    client.sendEvent(SIMULATION_STOPPED_EVENT);
                }

            } catch (Exception e) {
                LOGGER.error("Error when stopping simulation: {}", e.getMessage());
            }
        };
    }
}