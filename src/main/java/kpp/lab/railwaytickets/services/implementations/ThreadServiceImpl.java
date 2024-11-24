package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.config.ConfigFileGetter;
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
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

@Service
public class ThreadServiceImpl implements ThreadService {

    private final ClientCreatorService clientCreatorService;
    private final ClientCashDeskService clientCashDeskService;
    private final BaseLogger<CashDeskLogDto> cashDeskLogger;
    private final BaseTrainStation trainStation;

    private ExecutorService cashDeskExecutorService;
    private ExecutorService clientGeneratorExecutorService;
    private final ScheduledExecutorService scheduler;

    private final int clientsToBreakCashDesk;
    private final int restoreTimeMs;

    private final AtomicInteger currentClientsServed = new AtomicInteger(1);
    private final AtomicBoolean isThereABrokenCashDesk = new AtomicBoolean(false);

    public static final int SLEEP_TIME = 100;

    public ThreadServiceImpl(
            ClientCreatorService clientCreatorService,
            ClientCashDeskService clientCashDeskService,
            BaseTrainStation trainStation,
            BaseLogger<CashDeskLogDto> cashDeskLogger) {

        this.clientCreatorService = clientCreatorService;
        this.clientCashDeskService = clientCashDeskService;
        this.trainStation = trainStation;
        this.cashDeskLogger = cashDeskLogger;
        this.clientsToBreakCashDesk = ConfigFileGetter.get("cashDesk.clientsToBreakCashDesk", int.class);
        this.restoreTimeMs = ConfigFileGetter.get("cashDesk.restoreTimeMs", int.class);

        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void startCashDesks(SendCashDeskResponse sendCashDeskResponse, long applicationStartTime) {

        var cashDesks = trainStation.getCashDesks();

        cashDeskExecutorService = Executors.newFixedThreadPool(cashDesks.size());

        for (BaseCashDesk cashDesk : cashDesks) {
            cashDeskExecutorService.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        if (!cashDesk.getQueue().isEmpty()) {
                            if (currentClientsServed.incrementAndGet() % clientsToBreakCashDesk == 0
                                    && isThereABrokenCashDesk.compareAndSet(false, true)
                                    && !cashDesk.getIsBackup()) {
                                handleBrokenCashDesk(cashDesk, sendCashDeskResponse, applicationStartTime);
                            } else {
                                processClient(cashDesk, sendCashDeskResponse, applicationStartTime);
                            }
                        }

                        Thread.sleep(SLEEP_TIME);

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        LOGGER.info("Cash desk processing thread was interrupted for cash desk: {}", cashDesk.getId());
                        break;
                    } catch (NoSuchElementException e) {
                        LOGGER.warn("Queue is empty for cash desk: {}", cashDesk.getId());
                    } catch (Exception e) {
                        LOGGER.error("Error while processing order client: {}", e.getMessage());
                    }
                }

                LOGGER.info("Cash desk processing thread stopped for cash desk: {}", cashDesk.getId());
            });
        }
    }

    @Override
    public void stopCashDesks() {
        if (cashDeskExecutorService != null && !cashDeskExecutorService.isShutdown()) {
            LOGGER.info("Shutting down cash generation threads...");
            cashDeskExecutorService.shutdownNow();
            try {
                if (!cashDeskExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    LOGGER.warn("(stopCashDesks) Client generation thread did not terminate in the specified time.");
                }
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted while stopping cash desks.");
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void startClientGeneration(SendCreatedClientResponse responseFunction) {
        clientGeneratorExecutorService = Executors.newSingleThreadExecutor();
        clientGeneratorExecutorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    var createdClient = clientCreatorService.createClient();
                    clientCashDeskService.chooseCashDesk(createdClient);
                    responseFunction.execute(ClientMapper.baseClientToClientDto(createdClient));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.info("Client generation thread was interrupted.");
            } catch (Exception e) {
                LOGGER.error("Error while generating client: {}", e.getMessage());
            } finally {
                LOGGER.info("Client generation thread stopped.");
            }
        });
    }

    @Override
    public void stopClientGeneration() {
        shutdownExecutorService(cashDeskExecutorService, "cash desk processing thread pool");
        shutdownExecutorService(scheduler, "scheduler");
    }

    private void handleBrokenCashDesk(BaseCashDesk cashDesk, SendCashDeskResponse sendCashDeskResponse,
                                      long applicationStartTime) {

        clientCashDeskService.setDeskOutOfOrder(cashDesk);

        BaseClient clientToBeProcessed = cashDesk.getQueue().getFirst();

        long startTime = Instant.now().toEpochMilli() - applicationStartTime;
        sendCashDeskResponse.execute(CashDeskMapper.baseCashDeskToCashDeskDto(cashDesk));
        long endTime = Instant.now().toEpochMilli() - applicationStartTime;

        cashDeskLogger.write(new CashDeskLogDto(clientToBeProcessed.getId(),
                cashDesk.getId(),
                clientToBeProcessed.getTicketNumber(),
                startTime, endTime));

        clientCashDeskService.moveClientsToBackupQueue(cashDesk);

        scheduleBrokenCashDeskRepairment(cashDesk);
    }

    private void processClient(BaseCashDesk cashDesk, SendCashDeskResponse sendCashDeskResponse,
                               long applicationStartTime) throws Exception {
        BaseClient clientToBeProcessed = cashDesk.getQueue().getFirst();

        long startTime = Instant.now().toEpochMilli() - applicationStartTime;
        sendCashDeskResponse.execute(CashDeskMapper.baseCashDeskToCashDeskDto(
                clientCashDeskService.processOrder(cashDesk)
        ));
        long endTime = Instant.now().toEpochMilli() - applicationStartTime;

        logCashDesk(clientToBeProcessed, cashDesk, startTime, endTime);
    }

    private void scheduleBrokenCashDeskRepairment(BaseCashDesk cashDesk) {
        scheduler.schedule(() -> {
            clientCashDeskService.setDeskWorking(cashDesk);
            isThereABrokenCashDesk.set(false);
        }, restoreTimeMs, TimeUnit.MILLISECONDS);
    }

    private void logCashDesk(BaseClient clientToBeProcessed, BaseCashDesk cashDesk, long startTime, long endTime) {
        cashDeskLogger.write(new CashDeskLogDto(
                clientToBeProcessed.getId(),
                cashDesk.getId(),
                clientToBeProcessed.getTicketNumber(),
                startTime, endTime
        ));
    }

    private void shutdownExecutorService(ExecutorService executorService, String serviceName) {
        if (executorService != null && !executorService.isShutdown()) {
            LOGGER.info("Shutting down {}...", serviceName);
            executorService.shutdownNow();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    LOGGER.warn("({}) did not terminate in the specified time.", serviceName);
                }
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted while stopping {}.", serviceName);
                Thread.currentThread().interrupt();
            }
        }
    }
}