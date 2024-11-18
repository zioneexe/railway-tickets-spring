package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

import java.util.Comparator;
import java.util.List;

public class CashDeskSelectHelper {

    private CashDeskSelectHelper() {}

    private static int calcDistance(BaseClient client, BaseCashDesk cashDesk) {
        int result = Math.abs(client.getPosition().getX() - cashDesk.getPosition().getX());
        result += Math.abs(client.getPosition().getY() - cashDesk.getPosition().getY());
        return result;
    }

    public static BaseCashDesk selectBestDesk(List<BaseCashDesk> cashDesks, BaseClient client) {
        long minClientsNumberInQueue = cashDesks.stream()
                .mapToInt(cashDesk -> cashDesk.getQueue().size())
                .min()
                .orElse(0);
        List<BaseCashDesk> minClientsCashDesks =
                cashDesks.stream().filter(e -> e.getQueue().size() == (int)minClientsNumberInQueue).toList();

        return minClientsCashDesks.stream()
                .min(Comparator.comparing(cashDesk -> calcDistance(client, cashDesk)))
                .orElse(null);
    }
}
