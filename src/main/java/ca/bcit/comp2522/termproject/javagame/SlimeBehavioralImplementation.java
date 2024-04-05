package ca.bcit.comp2522.termproject.javagame;

import javafx.application.Platform;
import java.util.Random;


public class SlimeBehavioralImplementation implements SlimeBehaviour {
    public static final double MUTATION_COEFFICIENT = 0.5;
    private static final Random GENERATOR = new Random();


    public void split(PetriDish petriDish, Slime slime) {
        slime.shrinkSlimeToBaby();
        Slime slimeBaby;
        if (GENERATOR.nextDouble() > MUTATION_COEFFICIENT) {
            slimeBaby = mutate(slime.getCenterX(), slime.getCenterY(), petriDish);
        } else {
            slimeBaby = new YellowSlime(slime.getCenterX(), slime.getCenterY(), petriDish);
        }
        petriDish.addSlime(slimeBaby);
        slimeBaby.startThread();
        Platform.runLater(() -> {

            slimeBaby.addToPane(petriDish.getCanvas());
//            petriDish.getCanvas().getChildren().remove(slime.imageView);
        });

    }


    @Override
    public Slime mutate(double xCoordinator, double yCoordinator, PetriDish petriDish) {
        double slimeCoefficient = GENERATOR.nextDouble();
        if (slimeCoefficient <= 0.1) {
            return new PurpleSlime(xCoordinator, yCoordinator, petriDish);
        } else if (slimeCoefficient <= 0.3) {
            return new PinkSlime(xCoordinator, yCoordinator, petriDish);
        } else if (slimeCoefficient <= 0.6) {
            return new BlueSlime(xCoordinator, yCoordinator, petriDish);
        } else {
            return new GreenSlime(xCoordinator, yCoordinator, petriDish);
        }
    }
}
