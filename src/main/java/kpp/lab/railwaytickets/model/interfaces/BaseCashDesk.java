package kpp.lab.railwaytickets.model.interfaces;

import java.util.List;

public interface BaseCashDesk {

    int getId();

    BasePosition getPosition();

    void addClientToQueue(BaseClient client);

    List<BaseClient> getQueue();

    boolean getIsBackup();

    boolean getIsBroken();

    void setIsBroken(boolean isBroken);
}
