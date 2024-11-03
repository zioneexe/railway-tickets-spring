package kpp.lab.railwaytickets.model;

import java.util.UUID;

public interface BaseCashDesk {

    UUID getId();

    BasePosition getPosition();

    void addClientToQueue(BaseClient client);

    void removeClientFromQueue(BaseClient client);

    boolean getIsBackup();

    boolean getIsBroken();
}
