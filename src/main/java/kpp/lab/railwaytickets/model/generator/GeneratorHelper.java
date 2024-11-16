package kpp.lab.railwaytickets.model.generator;

import kpp.lab.railwaytickets.model.Client;
import kpp.lab.railwaytickets.model.Position;
import kpp.lab.railwaytickets.model.abstractions.BaseClient;
import kpp.lab.railwaytickets.model.decorator.*;

import java.util.HashMap;

public class GeneratorHelper {
    public static BaseClient DecorateClient(BaseClient client, HashMap<ClientDecorator, Double> clientDecoratorChances) {

        BaseClient client2;
        Double soldierDecoratorChance = clientDecoratorChances.get(ClientSoldierDecorator.class);

        if (soldierDecoratorChance != null && Math.random() < soldierDecoratorChance) {
            client2 = new ClientSoldierDecorator(client);  // Apply the decorator if the chance is met
        } else {
            client2 = client;  // No decorator applied
        }

        BaseClient client3;
        Double disabledDecoratorChance = clientDecoratorChances.get(DisabledClientDecorator.class);

        if (disabledDecoratorChance != null && Math.random() < disabledDecoratorChance) {
            client3 = new DisabledClientDecorator(client2);  // Apply the decorator if the chance is met
        } else {
            client3 = client2;  // No decorator applied
        }

        BaseClient client4;
        Double studentDecoratorChance = clientDecoratorChances.get(ClientStudentDecorator.class);

        if (studentDecoratorChance != null && Math.random() < studentDecoratorChance) {
            client4 = new ClientStudentDecorator(client3);  // Apply the decorator if the chance is met
        } else {
            client4 = client3;  // No decorator applied
        }

        BaseClient client5;
        Double withChildDecoratorChance = clientDecoratorChances.get(ClientWithChildDecorator.class);

        if (withChildDecoratorChance != null && Math.random() < withChildDecoratorChance) {
            client5 = new ClientWithChildDecorator(client4);  // Apply the decorator if the chance is met
        } else {
            client5 = client4;  // No decorator applied
        }

        return client5;
    }
}
