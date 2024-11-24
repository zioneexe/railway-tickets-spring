package kpp.lab.railwaytickets.model.interfaces;

public interface BaseClient {

    int getId();

    int getTicketNumber();

    String getType();

    BasePosition getPosition();

    void setPosition(BasePosition position);

    int calculatePriority();
}
