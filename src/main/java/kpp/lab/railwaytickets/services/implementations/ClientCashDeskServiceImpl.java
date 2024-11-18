package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.StartupProperties;
import kpp.lab.railwaytickets.model.TrainStation;
import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ClientCashDeskServiceImpl implements ClientCashDeskService {

    BaseStartupProperties startupProperties;
    BaseTrainStation trainStation;
    Random random = new Random();

    public ClientCashDeskServiceImpl(BaseStartupProperties startupProperties, BaseTrainStation trainStation) {
        this.startupProperties = startupProperties;
        this.trainStation = trainStation;
    }

    @Override
    public BaseCashDesk processOrder(BaseCashDesk cashDesk ) throws Exception {
        if (cashDesk.getQueue().isEmpty()) {
            throw new Exception("Cash desk has no clients in queue.");
        }

        BaseClient client = cashDesk.getQueue().removeFirst();

        try {
            Thread.sleep(getRandomBetween(startupProperties.getMinServiceTime(), startupProperties.getMaxServiceTime())
                    * client.getTicketNumber());
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return cashDesk;
    }

    private int getRandomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }


    @Override
    public BaseCashDesk chooseCashDesk(BaseClient client) throws Exception {

        var cashDesks = trainStation.getCashDesks();

        List<BaseCashDesk> workingCashDesks = cashDesks.stream().filter(e -> !e.getIsBroken() && !e.getIsBackup()).toList();

        BaseCashDesk chosenCashDesk = CashDeskSelectHelper.selectBestDesk(workingCashDesks, client);
        if (chosenCashDesk == null) {
            throw new Exception("Cash desk is null.");
        }

        chosenCashDesk.addClientToQueue(client);
        return chosenCashDesk;
    }

    @Override
    public void moveClientsToBackupQueue(BaseCashDesk baseCashDesk) {
/*
        backupCashDesk.getQueue().clear();
        backupCashDesk.getQueue().forEach(e -> baseCashDesk.getQueue().add(e));
        baseCashDesk.getQueue().clear();


 */
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
