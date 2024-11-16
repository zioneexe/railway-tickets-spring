package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;

import java.util.ArrayList;
import java.util.List;

public class CashDesk implements BaseCashDesk {

    private static int nextId = 1;

    private int id;

    private BasePosition position;

    private List<BaseClient> clientsQueue;

    private boolean isBackup;

    private boolean isBroken = false;

    public CashDesk(BasePosition position, boolean isBackup) {
        this.position = position;
        this.clientsQueue = new ArrayList<>();
        this.isBackup = isBackup;
        this.id = nextId++;
    }

    public CashDesk(int id, BasePosition position, boolean isBackup) {
        this.position = position;
        this.clientsQueue = new ArrayList<>();
        this.isBackup = isBackup;
        this.id = id;
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

        for (int i = clientsQueue.size() - 1; i >= 0; i--) {
            if(client.calculatePriority() <= clientsQueue.get(i).calculatePriority())
            {
                clientsQueue.add(i + 1, client);
                return;
            }
        }

        clientsQueue.add(0, client);
    }

    @Override
    public void removeClientFromQueue(BaseClient client) {
        clientsQueue.removeLast();
    }

    @Override
    public List<BaseClient> getQueue() { return clientsQueue; }

    @Override
    public boolean getIsBackup() {
        return isBackup;
    }

    @Override
    public boolean getIsBroken() {
        return isBroken;
    }

    @Override
    public void setIsBroken(boolean isBroken) { this.isBroken = isBroken; }
}
