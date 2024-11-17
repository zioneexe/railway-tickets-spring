package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public interface ClientCashDeskService {

    BaseCashDesk chooseCashDesk(BaseClient client);

    void addClientToQueue(BaseCashDesk cashDesk, BaseClient client);

    void moveClientsToBackupQueue(BaseCashDesk cashDesk);

    void setDeskOutOfOrder(BaseCashDesk cashDesk);

    void setDeskWorking(BaseCashDesk cashDesk);

    boolean checkClientLoad();

    int getClientsNumber();
}
