package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

@Service
public class ClientCashDeskServiceImpl implements ClientCashDeskService {

    private final BaseStartupProperties startupProperties;
    private final BaseTrainStation trainStation;
    private final Random random = new Random();

    public ClientCashDeskServiceImpl(BaseStartupProperties startupProperties, BaseTrainStation trainStation) {
        this.startupProperties = startupProperties;
        this.trainStation = trainStation;
    }

    @Override
    public synchronized BaseCashDesk processOrder(BaseCashDesk cashDesk) {

        var clientQueue = cashDesk.getQueue();
        BaseClient client = clientQueue.getFirst();

        try {
            Thread.sleep((long) getRandomBetween(
                    startupProperties.getMinServiceTime(),
                    startupProperties.getMaxServiceTime()
            ) * client.getTicketNumber());
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while processing the order.");
            Thread.currentThread().interrupt();
        }

        clientQueue.removeFirst();
        return cashDesk;
    }

    private int getRandomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }


    @Override
    public BaseCashDesk chooseCashDesk(BaseClient client) throws Exception {

        var cashDesks = trainStation.getCashDesks();

        List<BaseCashDesk> workingCashDesks = cashDesks.stream().filter(e -> !e.getIsBackup() && !e.getIsBroken()).toList();

        BaseCashDesk chosenCashDesk = CashDeskSelectHelper.selectBestDesk(workingCashDesks, client);
        if (chosenCashDesk == null) {
            throw new Exception("Cash desk is null.");
        }

        chosenCashDesk.addClientToQueue(client);
        return chosenCashDesk;
    }

    @Override
    public void moveClientsToBackupQueue(BaseCashDesk baseCashDesk) {

        BaseCashDesk backupCashDesk = trainStation.getCashDesks().stream().filter(e -> e.getIsBackup()).toList().get(0);
        baseCashDesk.getQueue().forEach(backupCashDesk.getQueue()::add);
        baseCashDesk.getQueue().clear();
    }

    @Override
    public void setDeskOutOfOrder(BaseCashDesk cashDesk) {
        cashDesk.setIsBroken(true);
    }

    @Override
    public void setDeskWorking(BaseCashDesk cashDesk) {
        cashDesk.setIsBroken(false);
    }
}
