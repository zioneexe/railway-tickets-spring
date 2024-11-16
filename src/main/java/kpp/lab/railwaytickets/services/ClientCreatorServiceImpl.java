package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

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
        //BaseClient client = clientGenerator.generateClients();
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
