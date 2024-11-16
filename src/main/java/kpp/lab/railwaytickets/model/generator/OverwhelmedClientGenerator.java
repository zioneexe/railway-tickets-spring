package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.services.Base.ClientCashDeskService;
import kpp.lab.railwaytickets.services.impl.ClientCashDeskServiceImpl;

import java.util.HashMap;

public class OverwhelmedClientGenerator implements BaseClientGenerator {

    private ClientCashDeskService cashDeskService;

    public OverwhelmedClientGenerator(ClientCashDeskService cashDeskService,  HashMap<ClientDecorator, Double> clientDecoratorChances) {
        this.cashDeskService = cashDeskService;
    }

    @Override
    public void generateClients() {
        try {
            // Sleep for 1000 milliseconds (1 second)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle the interruption (optional)
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        }
    }
}
