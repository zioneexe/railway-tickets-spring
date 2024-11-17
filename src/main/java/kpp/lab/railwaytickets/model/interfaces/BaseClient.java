package kpp.lab.railwaytickets.model.interfaces;

public interface BaseClient {

    // String getType();

    int getId();

    int getTicketNumber();

    BasePosition getPosition();
    void setPosition(BasePosition position);

    int calculatePriority();

    String getType();
}
