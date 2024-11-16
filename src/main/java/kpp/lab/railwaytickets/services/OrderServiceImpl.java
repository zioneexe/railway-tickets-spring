package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseLogger;
import kpp.lab.railwaytickets.model.BaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Процес обслуговування клієнта повинен бути зафіксований в журналі логування
 * з зазначеним номером каси, яка обслуговувала клієнта,
 * унікальним номером клієнта
 * та стартовим і кінцевим часом обслуговування клієнта біля каси.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final List<BaseLogger> orders;

    private final BaseLogger logger;

    @Autowired
    public OrderServiceImpl(BaseLogger logger) {
        this.logger = logger;
        this.orders = new LinkedList<>();
    }

    @Override
    public void processOrder(BaseOrder order) {

        logOrder(order);
    }

    private void logOrder(BaseOrder order) {
        logger.log("--order--");
        logger.log("Client: " + order.getClient().getId());
        logger.log("Tickets count: " + order.getTicketsCount());
        logger.log("Cash desk: " + order.getCashDesk().getId());
        logger.log("Start time:" + order.getStartTime());
        logger.log("End time:" + order.getEndTime());
    }
}
