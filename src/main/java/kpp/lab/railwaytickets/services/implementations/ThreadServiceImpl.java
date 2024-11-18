package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.mappers.CashDeskMapper;
import kpp.lab.railwaytickets.mappers.ClientMapper;
import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import kpp.lab.railwaytickets.services.interfaces.ThreadService;
import kpp.lab.railwaytickets.socket.SendCashDeskResponse;
import kpp.lab.railwaytickets.socket.SendCreatedClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {

    private ClientCreatorService clientCreatorService;

    private ClientCashDeskServiceImpl clientCashDeskService;

    private List<BaseCashDesk> cashDesks;

    private ExecutorService cashDeskExecutorService;
    private ExecutorService clientGeneratorExecutorService;

    public ThreadServiceImpl(ClientCreatorService clientCreatorService,
                             ClientCashDeskServiceImpl clientCashDeskService, List<BaseCashDesk> cashDesks
                             ) {
        this.clientCreatorService = clientCreatorService;
        this.clientCashDeskService = clientCashDeskService;
        this.cashDesks = cashDesks;
    }
    @Override
    public void startCashDesks(SendCashDeskResponse sendCashDeskResponse) {
        // Створюємо пул потоків із розміром, що дорівнює кількості кас
        cashDeskExecutorService = Executors.newFixedThreadPool(cashDesks.size());

        // Запускаємо кожну касу в окремому потоці
        for (BaseCashDesk cashDesk : cashDesks) {
            cashDeskExecutorService.submit(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            // Перевірка черги перед видаленням клієнта
                            if (!cashDesk.getQueue().isEmpty()) {
//                                BaseClient client = cashDesk.getQueue().removeFirst(); // Видаляємо першого клієнта
//                                clientCashDeskService.processOrder(client);
//                                log.info("Processed order for client: {}", client.getId());
                                sendCashDeskResponse.execute(CashDeskMapper.baseCashDeskToCashDeskDto(clientCashDeskService.processOrder(cashDesk)));
                            } else {
                                Thread.sleep(100);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            log.info("Cash desk processing thread was interrupted for cash desk: {}", cashDesk.getId());
                            break; // Виходимо з циклу при перериванні потоку
                        } catch (NoSuchElementException e) {
                            log.warn("Queue is empty for cash desk: {}", cashDesk.getId());
                        } catch (Exception e) {
                            log.error("Error while processing order client: {}", e.getMessage());
                        }
                    }
                } finally {
                    log.info("Cash desk processing thread stopped for cash desk: {}", cashDesk.getId());
                }
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
