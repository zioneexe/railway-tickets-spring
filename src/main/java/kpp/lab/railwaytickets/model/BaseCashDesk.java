package kpp.lab.railwaytickets.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

public interface BaseCashDesk {

    int getId();

    BasePosition getPosition();

    void addClientToQueue(BaseClient client);

    void removeClientFromQueue(BaseClient client);

    List<BaseClient> getQueue();

    boolean getIsBackup();

    boolean getIsBroken();

    void setIsBroken(boolean isBroken);
}
