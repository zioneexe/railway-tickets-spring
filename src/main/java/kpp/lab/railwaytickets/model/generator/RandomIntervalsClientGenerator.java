package kpp.lab.railwaytickets.model.generator;
import kpp.lab.railwaytickets.model.BaseClient;
import kpp.lab.railwaytickets.model.BasePosition;
import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.model.decorator.ClientSoldierDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Random;

@Slf4j
public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    private final Random random;
    private final int minServiceTime;
    private final int maxServiceTime;
    List<BasePosition> entrancePositions;
    @Value("${generator.tickets.min}")
    private final int minTicketsNumber = 1;
    @Value("${generator.tickets.max}")
    private final int maxTicketsNumber = 2;

    public RandomIntervalsClientGenerator(
            int minServiceTime,
            int maxServiceTime,
            List<BasePosition> entrancePositions
    ) {
        random = new Random();
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.entrancePositions = entrancePositions;
    }

    @Override
    public BaseClient generateClient() throws InterruptedException {
        var client = generateClientModel();
        waitFor(getRandomServiceTime() * client.getTicketNumber());
        return new ClientSoldierDecorator(client);
    }

    private int getRandomServiceTime() {
        return getRandomBetween(minServiceTime, maxServiceTime);
    }

    private void waitFor(int timeMs) throws InterruptedException {
        log.info("Waiting for {} milliseconds", timeMs);
        Thread.sleep(timeMs);
    }

    private BaseClient generateClientModel() {
        int randomEntranceIndex = random.nextInt(entrancePositions.size());
        var position = entrancePositions.get(randomEntranceIndex);
        var ticketsNumber = getRandomBetween(minTicketsNumber, maxTicketsNumber);

        return new Client(position, ticketsNumber);
    }

    private int getRandomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
