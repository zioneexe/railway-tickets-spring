package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseOrder;

public interface OrderService {

    void processOrder(BaseOrder order);
}
