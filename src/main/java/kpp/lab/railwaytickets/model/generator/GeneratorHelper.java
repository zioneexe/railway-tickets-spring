package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.decorator.*;
import lombok.extern.slf4j.Slf4j;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Random;

@Slf4j
public class GeneratorHelper implements BaseGeneratorHelper {

    private int minClientTiketsNumber = 1;
    private int maxClientTiketsNumber = 5;

    private double soldierDecoratorChance = 0.1;
    private double disabledDecoratorChance = 0.2;
    private double studentDecoratorChance = 0.3;
    private double withChildDecoratorChance = 0.4;

    Random random;

    public GeneratorHelper() {
        random = new Random();
    }

    public BaseClient decorateClient(BaseClient client) {

        if (Math.random() < soldierDecoratorChance) {
            client = new ClientSoldierDecorator(client);
        }

        if (Math.random() < disabledDecoratorChance) {
            client = new DisabledClientDecorator(client);
        }

        if (Math.random() < studentDecoratorChance) {
            client = new ClientStudentDecorator(client);
        }

        if (Math.random() < withChildDecoratorChance) {
            client = new ClientWithChildDecorator(client);
        }

        return client;
    }

    public void waitFor(int timeMs) throws InterruptedException {
        log.info("Generating client. It takes around " + timeMs + " milliseconds.");
        Thread.sleep(timeMs);
    }

    @Override
    public BaseClient createClient(List<BasePosition> entrancePositions) {

        var clientPosition = entrancePositions.get(random.nextInt(entrancePositions.size()));
        var clientTicketsNumber = getRandomBetween(minClientTiketsNumber, maxClientTiketsNumber);

        return decorateClient(new Client(clientPosition, clientTicketsNumber));
    }

    public int getRandomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
