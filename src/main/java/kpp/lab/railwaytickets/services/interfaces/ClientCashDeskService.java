package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseOrder;

import java.util.List;

public interface ClientCashDeskService {

    public BaseCashDesk processOrder(BaseCashDesk cashDesk);
    public BaseCashDesk chooseCashDesk(BaseClient client) throws Exception;
    public void moveClientsToBackupQueue(BaseCashDesk baseCashDesk);
    public void setDeskOutOfOrder(BaseCashDesk cashDesk);
    public void setDeskWorking(BaseCashDesk cashDesk);
}
