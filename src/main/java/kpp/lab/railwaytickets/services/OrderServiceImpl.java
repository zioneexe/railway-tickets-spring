package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.BaseLogger;
import kpp.lab.railwaytickets.model.BaseOrder;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private List<BaseLogger> orders;

    private BaseLogger logger;

    public OrderServiceImpl(BaseLogger logger) {

    }

    @Override
    public void processOrder(BaseOrder order) {

    }
}
