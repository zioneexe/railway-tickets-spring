package kpp.lab.railwaytickets.model.decorator;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

public class ClientStudentDecorator extends ClientDecorator{

    protected ClientStudentDecorator(BaseClient client) {
        super(client);
    }

    @Override
    public int calculatePriority() {
        return 0;
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
