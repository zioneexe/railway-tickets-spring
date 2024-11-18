package kpp.lab.railwaytickets.dto;

public class CashDeskLogDto {
    private final int clientId;
    private final int cashDeskId;
    private final int ticketsCount;
    private final int startTimeMs;
    private final int endTimeMs;

    public ClientDto(int id, PositionDto position, int ticketNumber, int priority, String type) {
        this.id = id;
        this.position = position;
        this.ticketNumber = ticketNumber;
        this.priority = priority;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public PositionDto getPosition() {
        return position;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() { return type ; }
}
