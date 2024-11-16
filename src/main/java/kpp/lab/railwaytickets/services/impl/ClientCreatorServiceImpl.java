package kpp.lab.railwaytickets.services.impl;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.services.ClientCreatorService;

import java.util.ArrayList;
import java.util.List;

public class ClientCreatorServiceImpl implements ClientCreatorService {

    private List<ClientCreatorSubscriber> subscribers;
    private BaseClientGenerator clientGenerator;

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
