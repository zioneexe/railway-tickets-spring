package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import kpp.lab.railwaytickets.services.interfaces.ThreadService;
import kpp.lab.railwaytickets.socket.SendCreatedClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {

    private ClientCreatorService clientCreatorService;

    private ExecutorService cashDeskExecutorService;
    private ExecutorService clientGeneratorExecutorService;

    public ThreadServiceImpl(ClientCreatorService clientCreatorService) {
        this.clientCreatorService = clientCreatorService;
    }

    @Override
    public void startCashDesks(List<BaseCashDesk> cashDesks) {

        cashDeskExecutorService = Executors.newFixedThreadPool(cashDesks.size());
        for (BaseCashDesk cashDesk : cashDesks) {
            cashDeskExecutorService.submit(() -> {

            });
        }
    }

    @Override
    public void stopCashDesks() {
        if (cashDeskExecutorService != null) {
            cashDeskExecutorService.shutdownNow();
        }
    }

    @Override
    public void startClientGeneration(SendCreatedClientResponse sendCreatedClientResponse) {
        clientGeneratorExecutorService = Executors.newSingleThreadExecutor();
        clientGeneratorExecutorService.submit(() -> {
            while (true) {
                try {
                    sendCreatedClientResponse.execute(clientCreatorService.createClient());
                } catch (InterruptedException e) {
                    log.error("Error while generating client: {}", e.getMessage());
                }
            }
        });
    }

    @Override
    public void stopClientGenerator() {
        if (clientGeneratorExecutorService != null) {
            clientGeneratorExecutorService.shutdownNow();
        }
    }

    @Override
    public void shutdownAll() {
        stopCashDesks();
        stopClientGenerator();
    }
}
