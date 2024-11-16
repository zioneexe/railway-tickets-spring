package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseOrder;

public interface OrderService extends Runnable {

    void processOrder(BaseOrder order);
}
