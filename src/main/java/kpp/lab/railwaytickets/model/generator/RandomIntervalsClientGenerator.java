package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.decorator.*;
import kpp.lab.railwaytickets.services.Base.ClientCashDeskService;

import java.util.HashMap;

public class RandomIntervalsClientGenerator implements BaseClientGenerator {

    private ClientCashDeskService cashDeskService;

    private HashMap<ClientDecorator, Double> clientDecoratorChances = new HashMap<>();
    public RandomIntervalsClientGenerator(ClientCashDeskService cashDeskService, HashMap<ClientDecorator, Double> clientDecoratorChances) {
        this.cashDeskService = cashDeskService;
        this.clientDecoratorChances = clientDecoratorChances;
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
