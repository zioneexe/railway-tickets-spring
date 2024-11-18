package kpp.lab.railwaytickets.services.implementations;

import com.fasterxml.jackson.databind.ser.Serializers;
import kpp.lab.railwaytickets.model.CashDeskSelectHelper;
import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.services.interfaces.ClientCashDeskService;
import kpp.lab.railwaytickets.services.interfaces.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCashDeskServiceImpl implements ClientCashDeskService {

    public ClientCashDeskServiceImpl() {
    }


    public BaseClient processOrder(BaseClient client) {
        int ticketsNumber = client.getTicketNumber();
        while(ticketsNumber-- > 0) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public BaseCashDesk processOrder(BaseCashDesk cashDesk ) {
        if (!cashDesk.getQueue().isEmpty()) {
            BaseClient client = cashDesk.getQueue().removeFirst();
            processOrder(client);
            return cashDesk;
        }
        return cashDesk;
    }

    @Override
    public BaseCashDesk chooseCashDesk(BaseClient client, List<BaseCashDesk> cashDesks) {
        List<BaseCashDesk> workingCashDesks =
                cashDesks.stream().filter(e -> !e.getIsBroken() && !e.getIsBackup()).toList();

        BaseCashDesk chosenCashDesk = CashDeskSelectHelper.selectBestDesk(workingCashDesks, client);
        return chosenCashDesk;
    }

    @Override
    public void addClientToQueue(BaseCashDesk cashDesk, BaseClient client) {
        cashDesk.addClientToQueue(client);
    }

    @Override
    public void moveClientsToBackupQueue(BaseCashDesk baseCashDesk) {

        backupCashDesk.getQueue().clear();
        backupCashDesk.getQueue().forEach(e -> baseCashDesk.getQueue().add(e));
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

    @Override
    public int getClientsNumber() {
        return cashDesks.stream().mapToInt(e -> e.getQueue().size()).sum();
    }
    @Override
    public boolean checkClientLoad() {
        return getClientsNumber() > outOfOrderMaxNumberOfOrders;
    }
}
