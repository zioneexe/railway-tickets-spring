package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.BaseCashDesk;
import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BaseOrder;

import java.util.List;

public interface ClientCashDeskService {

    BaseCashDesk chooseCashDesk(List<BaseCashDesk> baseCashDesks, BaseClient client);

    void addClientToQueue(BaseCashDesk cashDesk, BaseClient client);

    BaseOrder provideOrder(BaseCashDesk cashDesk);

    void moveClientsToBackupQueue(BaseCashDesk cashDesk);

    void setDeskOutOfOrder(BaseCashDesk cashDesk);

    void setDeskWorking(BaseCashDesk cashDesk);

    boolean checkClientLoad();
}
