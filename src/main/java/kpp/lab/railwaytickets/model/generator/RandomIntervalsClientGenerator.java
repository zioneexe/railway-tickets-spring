package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.decorator.*;
import kpp.lab.railwaytickets.services.Base.ClientCashDeskService;
import kpp.lab.railwaytickets.services.Base.ClientCreatorService;
import kpp.lab.railwaytickets.services.impl.ClientCreatorServiceImpl;

import java.util.HashMap;

public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    private ClientCashDeskService cashDeskService;
    private ClientCreatorService creatorService;

    private HashMap<ClientDecorator, Double> clientDecoratorChances = new HashMap<>();
    public RandomIntervalsClientGenerator(ClientCashDeskService cashDeskService,
                                          ClientCreatorServiceImpl clientCreatorService,
                                          HashMap<ClientDecorator, Double> clientDecoratorChances) {
        this.cashDeskService = cashDeskService;
        this.clientDecoratorChances = clientDecoratorChances;
        this.creatorService = clientCreatorService;
    }

    double minInterval = 0.5;  // 0.5 seconds
    double maxInterval = 2;    // 2 seconds

    @Override
    public void generateClients() {
        try {
            // Generate a random interval between minInterval and maxInterval (in seconds)
            double randomInterval = minInterval + (Math.random() * (maxInterval - minInterval));

            // Convert seconds to milliseconds
            long intervalInMilliseconds = (long) (randomInterval * 1000);

            // Sleep for the random interval
            Thread.sleep(intervalInMilliseconds);

            creatorService.createClient();
            // Now, you can generate the client
            // For example:
            // cashDeskService.createClient(); // Assuming createClient is a method in the cashDeskService

            // Print the interval for debugging purposes
            System.out.println("Generated client after " + randomInterval + " seconds.");

        } catch (InterruptedException e) {
            // Handle the interruption (optional)
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        }
    }
}
