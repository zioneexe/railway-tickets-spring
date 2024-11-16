package kpp.lab.railwaytickets.services.impl;

import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.generator.GeneratorHelper;
import kpp.lab.railwaytickets.services.Base.ClientCreatorService;

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
        BaseClient client = GeneratorHelper.DecorateClient(new Client(1, new Position(0 , 0), 1), decoratorChance);
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
