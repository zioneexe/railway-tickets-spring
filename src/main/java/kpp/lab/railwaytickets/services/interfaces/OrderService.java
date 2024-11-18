package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseOrder;

public interface OrderService {

    void processOrder(BaseOrder order);
}
