package kpp.lab.railwaytickets.services.impl;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.services.Base.ThreadService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServiceImpl implements ThreadService {


    double getChanceSoldier = 0.1;

    double getChanceStudent = 0.4;

    double getChanceDisabled = 0.2;

    double getChanceParent = 0.6;

    private ExecutorService cashDeskExecutorService;
    private ExecutorService clientGeneratorExecutorService;

    private ClientCreatorService clientCreatorService;
    private ClientCashDeskService clientCashDeskService;

    public ThreadServiceImpl(ClientCreatorService clientCreatorService, ClientCashDeskService clientCashDeskService) {
        this.clientCreatorService = clientCreatorService;
        this.clientCashDeskService = clientCashDeskService;
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
    public void startClientGenerator(BaseClientGenerator clientGenerator) {
        clientGeneratorExecutorService = Executors.newSingleThreadExecutor();
        clientGeneratorExecutorService.submit(clientGenerator::generateClients);
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
