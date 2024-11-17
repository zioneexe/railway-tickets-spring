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

    @Value("${clientGenerator.minClientTiketsNumber}")
    private int minClientTiketsNumber;
    @Value("${clientGenerator.maxClientTiketsNumber}")
    private int maxClientTiketsNumber;

    @Value("${clientGenerator.soldierDecoratorChance}")
    private double soldierDecoratorChance;
    @Value("${clientGenerator.disabledDecoratorChance}")
    private double disabledDecoratorChance;
    @Value("${clientGenerator.studentDecoratorChance}")
    private double studentDecoratorChance;
    @Value("${clientGenerator.withChildDecoratorChance}")
    private double withChildDecoratorChance;

    Random random;

    public GeneratorHelper() {
        random = new Random();
    }

    public BaseClient decorateClient(BaseClient client) {

        BaseClient client2;

        if (Math.random() < soldierDecoratorChance) {
            client2 = new ClientSoldierDecorator(client);  // Apply the decorator if the chance is met
        } else {
            client2 = client;  // No decorator applied
        }

        BaseClient client3;

        if (Math.random() < disabledDecoratorChance) {
            client3 = new DisabledClientDecorator(client2);  // Apply the decorator if the chance is met
        } else {
            client3 = client2;  // No decorator applied
        }

        BaseClient client4;

        if (Math.random() < studentDecoratorChance) {
            client4 = new ClientStudentDecorator(client3);  // Apply the decorator if the chance is met
        } else {
            client4 = client3;  // No decorator applied
        }

        BaseClient client5;

        if (Math.random() < withChildDecoratorChance) {
            client5 = new ClientWithChildDecorator(client4);  // Apply the decorator if the chance is met
        } else {
            client5 = client4;  // No decorator applied
        }

        return client5;
    }

    public void waitFor(int timeMs) throws InterruptedException {
        log.info("Generating client. It takes around" + timeMs + " milliseconds.");
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
