package kpp.lab.railwaytickets.dto;

public class ClientDto {

    private final int id;

    private final PositionDto position;

    private final int ticketNumber;

    private final int priority;

    private final String type;

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

