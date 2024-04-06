package ca.bcit.comp2522.termproject.javagame;

import javafx.application.Platform;
import java.util.Random;

/**
 * Represents the behavior of a slime, including splitting and mutation.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class SlimeBehavioralImplementation implements SlimeBehaviour {
    /**
     * Coefficient determining the likelihood of mutation during splitting.
     */
    public static final double MUTATION_COEFFICIENT = 0.5;
    private static final Random GENERATOR = new Random();

    /**
     * Splits the given slime into two and adds the new slime to the petri dish.
     * If mutation occurs, the new slime may have different characteristics.
     *
     * @param petriDish the petri dish to add the new slime to
     * @param slime     the slime to split
     */
    public void split(final PetriDish petriDish, final Slime slime) {
        slime.shrinkSlimeToBaby();
        Slime slimeBaby;
        if (GENERATOR.nextDouble() > MUTATION_COEFFICIENT) {
            slimeBaby = mutate(slime.getCenterX(), slime.getCenterY(), petriDish);
        } else {
            slimeBaby = new YellowSlime(slime.getCenterX(), slime.getCenterY(), petriDish);
        }
        petriDish.addSlime(slimeBaby);
        slimeBaby.startThread();
        Platform.runLater(() -> slimeBaby.addToPane(petriDish.getCanvas()));
    }

    /**
     * Mutates a slime at the given coordinates and returns the mutated slime.
     * The mutation chance depends on the random coefficient generated.
     *
     * @param xCoordinator the x-coordinate of the new slime
     * @param yCoordinator the y-coordinate of the new slime
     * @param petriDish    the petri dish to add the new slime to
     * @return the mutated slime
     */
    @Override
    public Slime mutate(final double xCoordinator, final double yCoordinator, final PetriDish petriDish) {
        double slimeCoefficient = GENERATOR.nextDouble();
        final double purpleThreshold = 0.1;
        final double pinkThreshold = 0.3;
        final double blueThreshold = 0.6;
        if (slimeCoefficient <= purpleThreshold) {
            return new PurpleSlime(xCoordinator, yCoordinator, petriDish);
        } else if (slimeCoefficient <= pinkThreshold) {
            return new PinkSlime(xCoordinator, yCoordinator, petriDish);
        } else if (slimeCoefficient <= blueThreshold) {
            return new BlueSlime(xCoordinator, yCoordinator, petriDish);
        } else {
            return new GreenSlime(xCoordinator, yCoordinator, petriDish);
        }
    }
}
