package kpp.lab.railwaytickets.dto;

import java.util.List;

public class CashDeskDto {
    private int id;
    private PositionDto position;
    private List<ClientDto> clientsQueue;
    private boolean isBackup;
    private boolean isBroken;

    public CashDeskDto(int id, PositionDto position, List<ClientDto> clientsQueue,  boolean isBackup, boolean isBroken) {
        this.position = position;
        this.clientsQueue = clientsQueue;
        this.isBackup = isBackup;
        this.id = id;
        this.isBroken = isBroken;
    }

    public int getId() { return id; }

    public PositionDto getPosition() { return position; }

    public List<ClientDto> getClientsQueue() { return this.clientsQueue; }

    public boolean getIsBackup() { return isBackup; }

    public boolean getIsBroken() { return isBroken; }
}

