package kpp.lab.railwaytickets.services;

import com.fasterxml.jackson.databind.ser.Serializers;
import kpp.lab.railwaytickets.model.CashDeskSelectHelper;
import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BaseOrder;

import java.util.List;

public class ClientCashDeskServiceImpl implements ClientCashDeskService {

    private OrderService orderService;

    private int outOfOrderMaxNumberOfOrders;

    private List<BaseCashDesk> cashDesks;

    private BaseCashDesk backupCashDesk;

    private double MAX_NUMBER_OF_CLIENTS_MULTIPLIER = 0.7;

    private int MaxNumberOfClients = (int)(cashDesks.stream().mapToInt(e -> e.getPosition().getY()).sum() * MAX_NUMBER_OF_CLIENTS_MULTIPLIER);

    public ClientCashDeskServiceImpl(OrderService orderService, List<BaseCashDesk> cashDesks, int minNum, int maxNum) {
        this.orderService = orderService;
        this.outOfOrderMaxNumberOfOrders = maxNum;
        this.cashDesks = cashDesks;
    }

    @Override
    public BaseCashDesk chooseCashDesk(BaseClient client) {
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
    public BaseOrder provideOrder(BaseCashDesk cashDesk) {
        return null;
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
