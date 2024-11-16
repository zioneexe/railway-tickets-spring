package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;

import java.util.UUID;

public class ClientStudentDecorator extends ClientDecorator{

    public ClientStudentDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 1;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getTicketNumber() {
        return 0;
    }

    @Override
    public BasePosition getPosition() {
        return null;
    }

    @Override
    public void setPosition(BasePosition position) {

    }
}
