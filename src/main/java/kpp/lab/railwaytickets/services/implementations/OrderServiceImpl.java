package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.interfaces.BaseLogger;
import kpp.lab.railwaytickets.model.interfaces.BaseOrder;
import kpp.lab.railwaytickets.services.interfaces.OrderService;
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

        logger.write(order);
    }
}
