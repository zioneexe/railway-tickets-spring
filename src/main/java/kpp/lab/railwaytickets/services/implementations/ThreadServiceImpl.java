package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.mappers.ClientMapper;
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
import java.util.concurrent.TimeUnit;

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
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    sendCreatedClientResponse.execute(ClientMapper.baseClientToClientDto(clientCreatorService.createClient()));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("Client generation thread was interrupted.");
            } catch (Exception e) {
                log.error("Error while generating client: {}", e.getMessage());
            } finally {
                log.info("Client generation thread stopped.");
            }
        });
    }

    @Override
    public void stopClientGeneration() {
        if (clientGeneratorExecutorService != null && !clientGeneratorExecutorService.isShutdown()) {
            log.info("Shutting down client generation thread...");
            clientGeneratorExecutorService.shutdownNow();
            try {
                if (!clientGeneratorExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    log.warn("Client generation thread did not terminate in the specified time.");
                }
            } catch (InterruptedException e) {
                log.error("Interrupted while waiting for client generation thread to terminate.");
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void shutdownAll() {
        stopCashDesks();
        stopClientGeneration();
    }
}
