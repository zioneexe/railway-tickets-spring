package kpp.lab.railwaytickets.services.Base;

import kpp.lab.railwaytickets.model.abstractions.BaseOrder;

public interface OrderService {

    void processOrder(BaseOrder order);
}
