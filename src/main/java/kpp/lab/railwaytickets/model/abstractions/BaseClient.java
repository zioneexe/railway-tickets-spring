package kpp.lab.railwaytickets.model.abstractions;

public interface BaseClient {

    String getType();

    int calculatePriority();

    int getId();

    int getTicketNumber();

    BasePosition getPosition();

    void setPosition(BasePosition position);
}
