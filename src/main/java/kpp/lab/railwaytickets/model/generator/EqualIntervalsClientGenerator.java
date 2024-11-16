package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.services.Base.ClientCashDeskService;
import kpp.lab.railwaytickets.services.impl.ClientCreatorServiceImpl;

import java.util.HashMap;

public class EqualIntervalsClientGenerator implements BaseClientGenerator {

    private ClientCashDeskService cashDeskService;

    private ClientCreatorServiceImpl creatorService;
    // Interval in seconds between client generations
    private double interval = 1.0; // 1 second

    public EqualIntervalsClientGenerator(ClientCashDeskService cashDeskService,
                                         HashMap<ClientDecorator, Double> clientDecoratorChances
    , ClientCreatorServiceImpl clientCreatorServiceImpl) {
        this.cashDeskService = cashDeskService;
        this.creatorService = clientCreatorServiceImpl;
    }

    @Override
    public void generateClients() {
        try {
            while (true) {
                // Create a client at equal intervals
                // Call method to create a client, e.g., cashDeskService.createClient();
                System.out.println("Generating client...");
                // Example: cashDeskService.createClient();
                creatorService.createClient();
                // Wait for the specified interval before generating the next client
                long intervalInMilliseconds = (long) (interval * 1000);
                Thread.sleep(intervalInMilliseconds);
            }
        } catch (InterruptedException e) {
            // Handle the exception if the thread is interrupted
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
