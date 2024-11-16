package kpp.lab.railwaytickets.dto;

public class ResultDto {
    private final int N;
    private final int M;

    private final int TotalClients;
    private final int TotalTickets;

    public ResultDto(int N, int M, int TotalClients, int TotalTickets) {
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
