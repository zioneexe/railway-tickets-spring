package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.configuration.ClientGeneratorProperties;
import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.decorator.ClientSoldierDecorator;
import kpp.lab.railwaytickets.model.decorator.ClientStudentDecorator;
import kpp.lab.railwaytickets.model.decorator.ClientWithChildDecorator;
import kpp.lab.railwaytickets.model.decorator.DisabledClientDecorator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BasePosition;

import java.util.List;
import java.util.Random;

import static kpp.lab.railwaytickets.RailwayTicketsApplication.LOGGER;

public class GeneratorHelper implements BaseGeneratorHelper {

    private final int minClientTicketsNumber;
    private final int maxClientTicketsNumber;

    private final double soldierDecoratorChance;
    private final double disabledDecoratorChance;
    private final double studentDecoratorChance;
    private final double withChildDecoratorChance;

    private static Random random;

    public GeneratorHelper(ClientGeneratorProperties clientGeneratorProperties) {
        this.minClientTicketsNumber = clientGeneratorProperties.getMinClientTicketsNumber();
        this.maxClientTicketsNumber = clientGeneratorProperties.getMaxClientTicketsNumber();
        this.soldierDecoratorChance = clientGeneratorProperties.getSoldierDecoratorChance();
        this.disabledDecoratorChance = clientGeneratorProperties.getDisabledDecoratorChance();
        this.studentDecoratorChance = clientGeneratorProperties.getStudentDecoratorChance();
        this.withChildDecoratorChance = clientGeneratorProperties.getWithChildDecoratorChance();
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
        LOGGER.info("Generating client. It takes around {} milliseconds.", timeMs);
        Thread.sleep(timeMs);
    }

    @Override
    public BaseClient createClient(List<BasePosition> entrancePositions) {

        var clientPosition = entrancePositions.get(random.nextInt(entrancePositions.size()));
        var clientTicketsNumber = getRandomBetween(minClientTicketsNumber, maxClientTicketsNumber);

        return decorateClient(new Client(clientPosition, clientTicketsNumber));
    }

    public int getRandomBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
