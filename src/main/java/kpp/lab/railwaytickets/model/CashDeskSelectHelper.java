package kpp.lab.railwaytickets.model;

import kpp.lab.railwaytickets.model.interfaces.BaseCashDesk;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;

import java.util.Comparator;
import java.util.List;

public class CashDeskSelectHelper {

    // TODO: додати на UML

    private CashDeskSelectHelper() {}

    public static int calcDistance(BaseClient client, BaseCashDesk cashDesk) {
        int result = Math.abs(client.getPosition().getX() - cashDesk.getPosition().getX());
        result += Math.abs(client.getPosition().getY() - cashDesk.getPosition().getY());
        return result;
    }

    public static BaseCashDesk selectBestDesk(List<BaseCashDesk> cashDesks, BaseClient client) {
        // Знайти мінімальну кількість клієнтів у черзі серед усіх кас
        long minClientsNumberInQueue = cashDesks.stream()
                .mapToInt(cashDesk -> cashDesk.getQueue().size()) // Отримати розмір черги для кожної каси
                .min() // Знайти мінімальний розмір черги
                .orElse(0); // Повернути 0, якщо жодна каса не знайдена
        List<BaseCashDesk> minClientsCashDesks =
                cashDesks.stream().filter(e -> e.getQueue().size() == (int)minClientsNumberInQueue).toList();

        return minClientsCashDesks.stream()
                .min(Comparator.comparing(cashDesk -> calcDistance(client, cashDesk)))
                .orElse(null);
    }
}
