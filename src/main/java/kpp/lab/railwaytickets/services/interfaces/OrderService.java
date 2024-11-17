package kpp.lab.railwaytickets.services.interfaces;

import kpp.lab.railwaytickets.model.interfaces.BaseOrder;

public interface OrderService extends Runnable {

    void processOrder(BaseOrder order);
}
