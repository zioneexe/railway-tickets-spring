package kpp.lab.railwaytickets.services.impl;

import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.decorator.ClientSoldierDecorator;
import kpp.lab.railwaytickets.model.decorator.ClientStudentDecorator;
import kpp.lab.railwaytickets.model.decorator.ClientWithChildDecorator;
import kpp.lab.railwaytickets.model.decorator.DisabledClientDecorator;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.services.ClientCreatorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientCreatorServiceImpl implements ClientCreatorService {

    private List<ClientCreatorSubscriber> subscribers;
    private BaseClientGenerator clientGenerator;

    Map<Class<? extends BaseClient>, Double> probabilities = new HashMap<>() {{
        put(Client.class, 0.8);
        put(ClientStudentDecorator.class, 0.2);
        put(ClientWithChildDecorator.class, 0.2);
        put(DisabledClientDecorator.class, 0.05);
        put(ClientSoldierDecorator.class, 0.1);
    }};

    public ClientCreatorServiceImpl(BaseClientGenerator clientGenerator) {
        this.clientGenerator = clientGenerator;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public BaseClient createClient() {
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

    @Override
    public BaseClientGenerator getClientGenerator() {
        return clientGenerator;
    }
}
