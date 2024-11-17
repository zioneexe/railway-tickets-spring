package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientCreatorServiceImpl implements ClientCreatorService {

    private List<ClientCreatorSubscriber> subscribers;

    public ClientCreatorServiceImpl( ) {
        this.subscribers = new ArrayList<>();
    }

    HashMap<ClientDecorator, Double> decoratorChance;

    @Override
    public BaseClient createClient() {
        // ЦЕ ЯКАСЬ ХЙУНЯ, ВОНО МАЄ БУТИ В ГЕНЕРАТОРАХ BaseClient client = GeneratorHelper.DecorateClient(new Client(1, new Position(0 , 0), 1), decoratorChance);
        // addSubscriber(client);
        notifySubscribersClientCreated();
        return null;
    }

    @Override
    public void addSubscriber(ClientCreatorSubscriber sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ClientCreatorSubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribersClientCreated() {
        for (ClientCreatorSubscriber sub : subscribers) {
            //sub.onClientCreated();
        }
    }
}
