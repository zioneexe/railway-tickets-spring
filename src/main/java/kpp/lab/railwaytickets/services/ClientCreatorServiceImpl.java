package kpp.lab.railwaytickets.services;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BaseStartupProperties;
import kpp.lab.railwaytickets.model.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.generator.RandomIntervalsClientGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ClientCreatorServiceImpl implements ClientCreatorService {

    /*
    private List<ClientCreatorSubscriber> subscribers;
    private BaseClientGenerator clientGenerator;

    public ClientCreatorServiceImpl(BaseClientGenerator clientGenerator) {
        this.clientGenerator = clientGenerator;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public BaseClient createClient() {
        //BaseClient client = clientGenerator.generateClients();
        notifySubscribersClientCreated();
        return null;
    }

    @Override
    public void addSubscriber(ClientCreatorSubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ClientCreatorSubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribersClientCreated() {
        for (ClientCreatorSubscriber sub : subscribers) {
            //sub.onClientCreated();
        }
    }
*/
    private final BaseStartupProperties startupProperties;
    private final SocketIOServer socketIOServer;
    private final ScheduledExecutorService executorService;


    public ClientCreatorServiceImpl(SocketIOServer socketIOServer, BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
        this.socketIOServer = socketIOServer;

        this.executorService = Executors.newSingleThreadScheduledExecutor();
        socketIOServer.start();
        socketIOServer.addConnectListener(client -> {
            log.info("Client connected: {}", client.getSessionId());
        });
    }

    public void startGenerating() {
        executorService.scheduleAtFixedRate(this::generateAndEmitClient, 0, 1, TimeUnit.SECONDS);
    }

    private void generateAndEmitClient() {
        try {
            var client = startupProperties.getClientGenerator().generateClient();
            log.info("Generated new client: {}", client);
            socketIOServer.getBroadcastOperations().sendEvent("newClient", client);
        } catch (Exception e) {
            log.error("Error generating client", e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
        socketIOServer.stop();
    }
}
