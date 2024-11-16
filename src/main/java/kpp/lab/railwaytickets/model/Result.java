package kpp.lab.railwaytickets.model;

public class Result {
    private int N;
    private int M;

    private int TotalClients;
    private int TotalTickets;

    public Result(int N, int M, int TotalClients, int TotalTickets) {
        this.N = N;
        this.M = M;
        this.TotalClients = TotalClients;
        this.TotalTickets = TotalTickets;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getTotalClients() {
        return TotalClients;
    }

    public int getTotalTickets() {
        return TotalTickets;
    }
}
