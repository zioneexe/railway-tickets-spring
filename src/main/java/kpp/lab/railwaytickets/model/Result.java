package kpp.lab.railwaytickets.model;

public class Result {

    private int TotalClients;
    private int TotalTickets;

    public Result(int TotalClients, int TotalTickets) {
        this.TotalClients = TotalClients;
        this.TotalTickets = TotalTickets;
    }

    public int getTotalClients() {
        return TotalClients;
    }

    public int getTotalTickets() {
        return TotalTickets;
    }
}
