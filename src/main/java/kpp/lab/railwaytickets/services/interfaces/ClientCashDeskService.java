package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public interface ClientCashDeskService {

    BaseCashDesk processOrder(BaseCashDesk cashDesk) throws Exception;
    BaseCashDesk chooseCashDesk(BaseClient client) throws Exception;
    void moveClientsToBackupQueue(BaseCashDesk baseCashDesk);
    void setDeskOutOfOrder(BaseCashDesk cashDesk);
    void setDeskWorking(BaseCashDesk cashDesk);
}
