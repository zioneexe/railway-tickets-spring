package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BaseOrder;

import java.util.List;

public class ClientCashDeskServiceImpl implements ClientCashDeskService {

    private OrderService orderService;

    private int outOfOrderMinNumberOfOrders;

    private int outOfOrderMaxNumberOfOrders;

    public ClientCashDeskServiceImpl(OrderService orderService, int minNum, int maxNum) {

    }

    @Override
    public BaseCashDesk chooseCashDesk(List<BaseCashDesk> baseCashDesks, BaseClient client) {
        return null;
    }

    @Override
    public void addClientToQueue(BaseCashDesk cashDesk, BaseClient client) {

    }

    @Override
    public BaseOrder provideOrder(BaseCashDesk cashDesk) {
        return null;
    }

    @Override
    public void moveClientsToBackupQueue(BaseCashDesk cashDesk) {

    }

    @Override
    public void setDeskOutOfOrder(BaseCashDesk cashDesk) {

    }

    @Override
    public void setDeskWorking(BaseCashDesk cashDesk) {

    }

    @Override
    public boolean checkClientLoad() {
        return false;
    }
}
