package kpp.lab.railwaytickets.validation;

import kpp.lab.railwaytickets.config.ConfigFileGetter;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationPropertiesValidator {
    private static final Set<String> violations = new HashSet<>();

    public static Set<String> validate() {

        // Client generator settings
        int clientGeneratorTimeMs = ConfigFileGetter.get("clientGenerator\\equal\\generationTimeMs", int.class);
        int randomGeneratorMinTimeMs = ConfigFileGetter.get("clientGenerator\\random\\minGenerationTimeMs", int.class);
        int randomGeneratorMaxTimeMs = ConfigFileGetter.get("clientGenerator\\random\\maxGenerationTimeMs", int.class);
        int increasingGeneratorStartTimeMs = ConfigFileGetter.get("clientGenerator\\increasing\\startGenerationTimeMs", int.class);
        int increasingGeneratorIncrementTimeMs = ConfigFileGetter.get("clientGenerator\\increasing\\incrementTimeMs", int.class);

        // Ticket settings
        int minClientTicketsNumber = ConfigFileGetter.get("tickets\\minClientTicketsNumber", int.class);
        int maxClientTicketsNumber = ConfigFileGetter.get("tickets\\maxClientTicketsNumber", int.class);

        // Decorator settings
        double soldierDecoratorChance = ConfigFileGetter.get("decorator\\soldierDecoratorChance", double.class);
        double disabledDecoratorChance = ConfigFileGetter.get("decorator\\disabledDecoratorChance", double.class);
        double studentDecoratorChance = ConfigFileGetter.get("decorator\\studentDecoratorChance", double.class);
        double withChildDecoratorChance = ConfigFileGetter.get("decorator\\withChildDecoratorChance", double.class);

        // Cash desk settings
        int clientsToBreakCashDesk = ConfigFileGetter.get("cashDesk\\clientsToBreakCashDesk", int.class);
        int restoreTimeMs = ConfigFileGetter.get("cashDesk\\restoreTimeMs", int.class);

        // Validation logic for violations (if any)

        if (clientGeneratorTimeMs <= 0) {
            violations.add("clientGenerator.equal.generationTimeMs must be positive");
        }
        if (randomGeneratorMinTimeMs <= 0 || randomGeneratorMaxTimeMs <= 0) {
            violations.add("clientGenerator.random time values must be positive");
        }
        if (increasingGeneratorStartTimeMs <= 0 || increasingGeneratorIncrementTimeMs <= 0) {
            violations.add("clientGenerator.increasing time values must be positive");
        }
        if (minClientTicketsNumber <= 0 || maxClientTicketsNumber <= 0) {
            violations.add("tickets numbers must be positive");
        }
        if (soldierDecoratorChance < 0 || soldierDecoratorChance > 1) {
            violations.add("soldierDecoratorChance must be between 0 and 1");
        }
        if (disabledDecoratorChance < 0 || disabledDecoratorChance > 1) {
            violations.add("disabledDecoratorChance must be between 0 and 1");
        }
        if (studentDecoratorChance < 0 || studentDecoratorChance > 1) {
            violations.add("studentDecoratorChance must be between 0 and 1");
        }
        if (withChildDecoratorChance < 0 || withChildDecoratorChance > 1) {
            violations.add("withChildDecoratorChance must be between 0 and 1");
        }
        if (clientsToBreakCashDesk <= 0) {
            violations.add("clientsToBreakCashDesk must be positive");
        }
        if (restoreTimeMs <= 0) {
            violations.add("restoreTimeMs must be positive");
        }

        return violations;
    }
}
