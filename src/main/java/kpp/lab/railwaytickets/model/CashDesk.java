package kpp.lab.railwaytickets.model;

import java.util.PriorityQueue;
import java.util.UUID;

public class CashDesk implements BaseCashDesk {

    private UUID id;

    private BasePosition position;

    private PriorityQueue<BaseClient> clients;

    private boolean isBackup;

    private boolean isBroken = false;

    public CashDesk(BasePosition position, boolean isBackup) {
        this.position = position;
        this.clients = new PriorityQueue<BaseClient>();
        this.isBackup = isBackup;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public BasePosition getPosition() {
        return position;
    }

    @Override
    public void addClientToQueue(BaseClient client) {
        clients.add(client);
    }

    @Override
    public void removeClientFromQueue(BaseClient client) {
        clients.remove(client);
    }

    @Override
    public boolean getIsBackup() {
        return isBackup;
    }

    @Override
    public boolean getIsBroken() {
        return isBroken;
    }
}
