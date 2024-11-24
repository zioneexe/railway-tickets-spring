package kpp.lab.railwaytickets.model;

import lombok.Getter;

@Getter
public class Result {

    private int totalClients;
    private int totalTickets;

    public Result(int totalClients, int totalTickets) {
        this.totalClients = totalClients;
        this.totalTickets = totalTickets;
    }
}
