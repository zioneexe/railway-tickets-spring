package kpp.lab.railwaytickets.model.abstractions;

import java.util.List;

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
