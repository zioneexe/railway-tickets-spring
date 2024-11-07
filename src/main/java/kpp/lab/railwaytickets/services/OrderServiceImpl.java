package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.BaseLogger;
import kpp.lab.railwaytickets.model.BaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private List<BaseLogger> orders;

    private BaseLogger logger;

    @Autowired
    public OrderServiceImpl(BaseLogger logger) {

    }

    @Override
    public void processOrder(BaseOrder order) {

    }
}
