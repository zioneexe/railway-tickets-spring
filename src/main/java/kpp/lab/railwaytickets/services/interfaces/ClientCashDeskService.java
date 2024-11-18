package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseOrder;

import java.util.List;

public interface ClientCashDeskService {

    BaseCashDesk chooseCashDesk(BaseClient client);

    void addClientToQueue(BaseCashDesk cashDesk, BaseClient client);

    void moveClientsToBackupQueue(BaseCashDesk cashDesk);

    void setDeskOutOfOrder(BaseCashDesk cashDesk);

    void setDeskWorking(BaseCashDesk cashDesk);

    boolean checkClientLoad();

    BaseCashDesk processOrder(BaseCashDesk cashDesk);

    BaseClient processOrder(BaseClient client);

    int getClientsNumber();

    BaseCashDesk chooseCashDesk(BaseClient client, List<BaseCashDesk> cashDesks);
}
