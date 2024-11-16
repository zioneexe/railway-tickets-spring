package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.BaseOrder;

import java.util.List;

public interface ClientCashDeskService {

    BaseCashDesk chooseCashDesk(BaseClient client);

    void addClientToQueue(BaseCashDesk cashDesk, BaseClient client);

    void moveClientsToBackupQueue(BaseCashDesk cashDesk);

    void setDeskOutOfOrder(BaseCashDesk cashDesk);

    void setDeskWorking(BaseCashDesk cashDesk);

    boolean checkClientLoad();

    int getClientsNumber();
}
