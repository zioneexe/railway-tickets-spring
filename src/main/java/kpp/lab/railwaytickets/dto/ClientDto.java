package kpp.lab.railwaytickets.dto;

public class ClientDto {

    private final int id;

    private final PositionDto position;

    private final int ticketNumber;

    private final int priority;

    public ClientDto(int id, PositionDto position, int ticketNumber, int priority) {
        this.id = id;
        this.position = position;
        this.ticketNumber = ticketNumber;
        this.priority = priority;
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
}

