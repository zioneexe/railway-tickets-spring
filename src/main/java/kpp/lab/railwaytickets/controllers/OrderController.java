package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String showOrdersLog() {
        return null;
    }
}
