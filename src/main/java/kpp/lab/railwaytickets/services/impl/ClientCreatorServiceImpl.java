package kpp.lab.railwaytickets.services.impl;

import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.abstractions.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.decorator.*;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.generator.GeneratorHelper;
import kpp.lab.railwaytickets.services.Base.ClientCreatorService;

import java.util.*;

public class ClientCreatorServiceImpl implements ClientCreatorService {

    private int nextId = 1;

    private final Random random = new Random();

    private final List<BasePosition> entrancePositions;
    private final List<ClientCreatorSubscriber> subscribers;
    private final BaseClientGenerator clientGenerator;

    Map<Class<? extends BaseClient>, Double> probabilities = new HashMap<>() {{
        put(Client.class, 0.8);
        put(ClientStudentDecorator.class, 0.2);
        put(ClientWithChildDecorator.class, 0.2);
        put(DisabledClientDecorator.class, 0.05);
        put(ClientSoldierDecorator.class, 0.1);
    }};

    Map<Class<? extends BaseClient>, Class<? extends BaseClient>> prohibitedCombinations = new HashMap<>() {{
       put(ClientSoldierDecorator.class, DisabledClientDecorator.class);
       put(ClientStudentDecorator.class, ClientSoldierDecorator.class);
    }};

    public ClientCreatorServiceImpl(BaseClientGenerator clientGenerator, BaseTrainStation trainStation) {
        this.clientGenerator = clientGenerator;
        this.entrancePositions = trainStation.getEntrances().stream().map(BaseEntrance::getPosition).toList();
        this.subscribers = new ArrayList<>();
    }

    HashMap<ClientDecorator, Double> decoratorChance;

    @Override
    public BaseClient createClient() {
        BaseClient client = GeneratorHelper.DecorateClient(new Client(1, new Position(0 , 0), 1), decoratorChance);
//        addSubscriber(client);
        notifySubscribersClientCreated();
        return null;
    }

    private List<Class<? extends BaseClient>> getSelectedDecorators() {
        List<Class<? extends BaseClient>> selectedDecorators = new ArrayList<>();

        if (Math.random() < 0.1) {
            addDecoratorIfSelected(selectedDecorators, ClientStudentDecorator.class);
            addDecoratorIfSelected(selectedDecorators, ClientWithChildDecorator.class);
            addDecoratorIfSelected(selectedDecorators, DisabledClientDecorator.class);
            addDecoratorIfSelected(selectedDecorators, ClientSoldierDecorator.class);
        } else {
            addSingleDecorator(selectedDecorators);
        }

        return selectedDecorators;
    }

    private void addDecoratorIfSelected(List<Class<? extends BaseClient>> decorators, Class<? extends BaseClient> decoratorClass) {
        if (random.nextDouble() < probabilities.getOrDefault(decoratorClass, 0.0)) {
            decorators.add(decoratorClass);
        }
    }

    private void addSingleDecorator(List<Class<? extends BaseClient>> decorators) {
        double probability = random.nextDouble();
        if (probability < probabilities.getOrDefault(ClientStudentDecorator.class, 0.0)) {
            decorators.add(ClientStudentDecorator.class);
        } else if (probability < probabilities.getOrDefault(ClientWithChildDecorator.class, 0.0)) {
            decorators.add(ClientWithChildDecorator.class);
        } else if (probability < probabilities.getOrDefault(DisabledClientDecorator.class, 0.0)) {
            decorators.add(DisabledClientDecorator.class);
        } else if (probability < probabilities.getOrDefault(ClientSoldierDecorator.class, 0.0)) {
            decorators.add(ClientSoldierDecorator.class);
        }
    }

    private BaseClient applyDecorators(BaseClient client, List<Class<? extends BaseClient>> decorators) {
        for (Class<? extends BaseClient> decoratorClass : decorators) {
            client = wrapWithDecorator(client, decoratorClass);
        }
        return client;
    }

    private BaseClient wrapWithDecorator(BaseClient baseClient, Class<? extends BaseClient> decoratorClass) {
        try {
            return decoratorClass.getConstructor(BaseClient.class).newInstance(baseClient);
        } catch (Exception e) {
            throw new RuntimeException("Error applying decorator: " + decoratorClass.getSimpleName(), e);
        }
    }

    private boolean isValidCombination(List<Class<? extends BaseClient>> appliedDecorators) {
        for (Map.Entry<Class<? extends BaseClient>, Class<? extends BaseClient>> entry : prohibitedCombinations.entrySet()) {
            if (appliedDecorators.contains(entry.getKey()) && appliedDecorators.contains(entry.getValue())) {
                return false;
            }
        }
        return true;
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
    public void notifySubscribersClientCreated(BaseClient client) {
        for (ClientCreatorSubscriber sub : subscribers) {
            sub.onClientCreated(client);
        }
    }

    @Override
    public BaseClientGenerator getClientGenerator() {
        return clientGenerator;
    }
}
