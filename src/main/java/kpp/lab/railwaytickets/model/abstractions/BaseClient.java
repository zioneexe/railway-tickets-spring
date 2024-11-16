package kpp.lab.railwaytickets.model.abstractions;

public interface BaseClient {

    int calculatePriority();

    int getId();

    int getTicketNumber();

    BasePosition getPosition();

    void setPosition(BasePosition position);
}
