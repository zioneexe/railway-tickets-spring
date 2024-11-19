package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

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
// 1
        // 3, 4, 2, 2  - 5
        for (int i = clientsQueue.size() - 1; i >= 1; i--) {
            if(client.calculatePriority() <= clientsQueue.get(i).calculatePriority())
            {
                clientsQueue.add(i + 1, client);
                return;
            }
        }
        if (clientsQueue.isEmpty()) {
            clientsQueue.add(0, client);
        } else {
            clientsQueue.add(1, client);
        }
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
