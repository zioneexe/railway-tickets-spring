package kpp.lab.railwaytickets;

import kpp.lab.railwaytickets.model.CashDesk;
import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static kpp.lab.railwaytickets.model.CashDeskSelectHelper.selectBestDesk;

@SpringBootTest
public class CashDeskHelperTest {

    @Test
    public void SelectBestDesk_NoDesks_ReturnsNull() {
        // Порожній список касових пунктів
        List<BaseCashDesk> cashDesks = new ArrayList<>();
        Position clientPos = new Position(5, 5);
        BaseClient client = new Client(0,clientPos, 1);

        // Метод для вибору кращого касового пункту
        BaseCashDesk selectedDesk = selectBestDesk(cashDesks, client);

        // Перевірка, чи повертається null
        Assertions.assertNull(selectedDesk, "Якщо немає касових пунктів з чергами, має бути повернено null");
    }

    @Test
    public void SelectBestDesk_OneDeskWithNoQueue_ReturnsThisDesk() {
        List<BaseCashDesk> cashDesks = new ArrayList<>();
        Position p1 = new Position(0, 0);
        Position clientPos = new Position(5, 5);
        BaseClient client = new Client(0,clientPos, 1);

        CashDesk desk1 = new CashDesk(p1, false);
        cashDesks.add(desk1);

        BaseCashDesk selectedDesk = selectBestDesk(cashDesks, client);

        Assertions.assertEquals(desk1, selectedDesk, "Каса без черги повинна бути вибрана");
    }

    @Test
    public void SelectBestDesk_MultipleDesksDifferentQueueSizes_ReturnsDeskWithMinQueue() {
        List<BaseCashDesk> cashDesks = new ArrayList<>();
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 1);
        Position clientPos = new Position(2, 2);

        CashDesk desk1 = new CashDesk(p1, false);
        CashDesk desk2 = new CashDesk(p2, false);

        // Додаємо клієнтів у чергу другої каси
        desk1.getQueue().add(new Client(0,new Position(3, 3), 1));
        desk1.getQueue().add(new Client(1,new Position(4, 4), 1));
        desk2.getQueue().add(new Client(2,new Position(5, 5), 1));

        cashDesks.add(desk1);
        cashDesks.add(desk2);

        BaseClient client = new Client(3,clientPos, 1);
        BaseCashDesk selectedDesk = selectBestDesk(cashDesks, client);

        Assertions.assertEquals(desk2, selectedDesk, "Каса з найменшою чергою повинна бути вибрана");
    }

    @Test
    public void SelectBestDesk_DesksWithEqualQueues_ReturnsNearestDesk() {
        List<BaseCashDesk> cashDesks = new ArrayList<>();
        Position p1 = new Position(0, 0);
        Position p2 = new Position(5, 5);
        Position clientPos = new Position(2, 2);

        CashDesk desk1 = new CashDesk(p1, false);
        CashDesk desk2 = new CashDesk(p2, false);

        // Додаємо клієнта в обидві черги
        desk1.getQueue().add(new Client(0,new Position(1, 1), 1));
        desk2.getQueue().add(new Client(1,new Position(1, 1), 1));

        cashDesks.add(desk1);
        cashDesks.add(desk2);

        BaseClient client = new Client(2,clientPos, 1);
        BaseCashDesk selectedDesk = selectBestDesk(cashDesks, client);

        Assertions.assertEquals(desk1, selectedDesk, "Ближча каса повинна бути вибрана, якщо розмір черг однаковий");
    }

    @Test
    public void SelectBestDesk_DesksWithDifferentDistancesAndEqualQueues_ReturnsNearestDesk() {
        List<BaseCashDesk> cashDesks = new ArrayList<>();
        Position p1 = new Position(0, 0);
        Position p2 = new Position(10, 10);
        Position clientPos = new Position(2, 2);

        CashDesk desk1 = new CashDesk(p1, false);
        CashDesk desk2 = new CashDesk(p2, false);

        // Додаємо клієнтів у чергу обох кас
        desk1.getQueue().add(new Client(0,new Position(1, 1), 1));
        desk2.getQueue().add(new Client(1,new Position(1, 1), 1));

        cashDesks.add(desk1);
        cashDesks.add(desk2);

        BaseClient client = new Client(2,clientPos, 1);
        BaseCashDesk selectedDesk = selectBestDesk(cashDesks, client);

        Assertions.assertEquals(desk1, selectedDesk, "Ближча каса повинна бути вибрана, якщо відстань різна, а черги однакові");
    }
}
