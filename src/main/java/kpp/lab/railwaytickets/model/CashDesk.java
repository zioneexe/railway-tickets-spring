package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.ArrayList;
import java.util.List;

public class CashDesk implements BaseCashDesk {

    private static int nextId = 1;
    private final int id;

    private final BasePosition position;

    private final List<BaseClient> clientsQueue;

    private final boolean isBackup;

    private boolean isBroken = false;

    public CashDesk(BasePosition position, boolean isBackup) {
        this.position = position;
        this.clientsQueue = new ArrayList<>();
        this.isBackup = isBackup;
        this.id = nextId++;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public BasePosition getPosition() {
        return position;
    }

    @Override
    public void addClientToQueue(BaseClient client) {
        for (int i = clientsQueue.size() - 1; i >= 1; i--) {
            if (client.calculatePriority() <= clientsQueue.get(i).calculatePriority()) {
                clientsQueue.add(i + 1, client);
                return;
            }
        }
        if (clientsQueue.isEmpty()) {
            clientsQueue.addFirst(client);
        } else {
            clientsQueue.add(1, client);
        }
    }

    @Override
    public List<BaseClient> getQueue() {
        return clientsQueue;
    }

    @Override
    public boolean getIsBackup() {
        return isBackup;
    }

    @Override
    public boolean getIsBroken() {
        return isBroken;
    }

    @Override
    public void setIsBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }
}
