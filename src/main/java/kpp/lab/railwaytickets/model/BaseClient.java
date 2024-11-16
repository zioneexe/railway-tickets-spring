package kpp.lab.railwaytickets.model;

import java.util.UUID;

public interface BaseClient {

    int calculatePriority();

    int getId();

    int getTicketNumber();

    BasePosition getPosition();

    void setPosition(BasePosition position);
}
