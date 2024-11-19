package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

public interface ClientCashDeskService {

    public BaseCashDesk processOrder(BaseCashDesk cashDesk) throws Exception;
    public BaseCashDesk chooseCashDesk(BaseClient client) throws Exception;
    public void moveClientsToBackupQueue(BaseCashDesk baseCashDesk);
    public void setDeskOutOfOrder(BaseCashDesk cashDesk);
    public void setDeskWorking(BaseCashDesk cashDesk);
}
