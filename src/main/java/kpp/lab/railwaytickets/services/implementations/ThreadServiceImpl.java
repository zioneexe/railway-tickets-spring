package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.dto.CashDeskLogDto;
import kpp.lab.railwaytickets.mappers.CashDeskMapper;
import kpp.lab.railwaytickets.mappers.ClientMapper;
import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseLogger;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import kpp.lab.railwaytickets.services.interfaces.ThreadService;
import kpp.lab.railwaytickets.socket.SendCashDeskResponse;
import kpp.lab.railwaytickets.socket.SendCreatedClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {

    private ClientCreatorService clientCreatorService;
    private ClientCashDeskService clientCashDeskService;
    private BaseTrainStation trainStation;

    private ExecutorService cashDeskExecutorService;
    private ExecutorService clientGeneratorExecutorService;

    private AtomicInteger currentClientsServed = new AtomicInteger(1);

    private final int clientsToBreakCashDesk = 10;
    private final int restoreTimeMs = 20000;

    private boolean isThereABrokenCashDesk = false;

    private BaseLogger<CashDeskLogDto> cashDeskLogger;


    public ThreadServiceImpl(
            ClientCreatorService clientCreatorService,
            ClientCashDeskService clientCashDeskService,
            BaseTrainStation trainStation,
            BaseLogger<CashDeskLogDto> cashDeskLogger) {

        this.clientCreatorService = clientCreatorService;
        this.clientCashDeskService = clientCashDeskService;
        this.trainStation = trainStation;
        this.cashDeskLogger = cashDeskLogger;
    }

    @Override
    public void startCashDesks(SendCashDeskResponse sendCashDeskResponse, long startSimulationTime) {

        var cashDesks = trainStation.getCashDesks();

        cashDeskExecutorService = Executors.newFixedThreadPool(cashDesks.size());

        for (BaseCashDesk cashDesk : cashDesks) {
            cashDeskExecutorService.submit(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            if (!cashDesk.getQueue().isEmpty()) {
                                if (currentClientsServed.incrementAndGet() % clientsToBreakCashDesk == 0 && !isThereABrokenCashDesk) {

                                    clientCashDeskService.setDeskOutOfOrder(cashDesk);
                                    isThereABrokenCashDesk = true;

                                    BaseClient clientToBeProcessed = cashDesk.getQueue().getFirst();

                                    long startTime = Instant.now().toEpochMilli() - startSimulationTime;
                                    sendCashDeskResponse.execute(CashDeskMapper.baseCashDeskToCashDeskDto(cashDesk));
                                    long endTime = Instant.now().toEpochMilli() - startSimulationTime;

                                    cashDeskLogger.write(new CashDeskLogDto(clientToBeProcessed.getId(), cashDesk.getId(), clientToBeProcessed.getTicketNumber(), startTime, endTime));

                                    clientCashDeskService.moveClientsToBackupQueue(cashDesk);

                                    var scheduler = Executors.newScheduledThreadPool(1);
                                    scheduler.schedule(() -> {
                                        clientCashDeskService.setDeskWorking(cashDesk);
                                        isThereABrokenCashDesk = false;
                                    }, restoreTimeMs, TimeUnit.MILLISECONDS);

                                } else {
                                    BaseClient clientToBeProcessed = cashDesk.getQueue().getFirst();

                                    long startTime = Instant.now().toEpochMilli() - startSimulationTime;
                                    sendCashDeskResponse.execute(CashDeskMapper.baseCashDeskToCashDeskDto(clientCashDeskService.processOrder(cashDesk)));
                                    long endTime = Instant.now().toEpochMilli()- startSimulationTime;

                                    cashDeskLogger.write(new CashDeskLogDto(clientToBeProcessed.getId(), cashDesk.getId(), clientToBeProcessed.getTicketNumber(), startTime, endTime));
                                }
                            }

                            Thread.sleep(100);

                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            log.info("Cash desk processing thread was interrupted for cash desk: {}", cashDesk.getId());
                            break;
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
        if (cashDeskExecutorService != null && !cashDeskExecutorService.isShutdown()) {
            log.info("Shutting down cash generation threads...");
            cashDeskExecutorService.shutdownNow();
            try {
                if (!cashDeskExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    log.warn("Client generation thread did not terminate in the specified time.");
                }
            } catch (InterruptedException e) {
                log.error("Interrupted while waiting for client generation thread to terminate.");
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void startClientGeneration(SendCreatedClientResponse sendCreatedClientResponse) {
        clientGeneratorExecutorService = Executors.newSingleThreadExecutor();
        clientGeneratorExecutorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    var createdClient = clientCreatorService.createClient();
                    clientCashDeskService.chooseCashDesk(createdClient);
                    sendCreatedClientResponse.execute(ClientMapper.baseClientToClientDto(createdClient));
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