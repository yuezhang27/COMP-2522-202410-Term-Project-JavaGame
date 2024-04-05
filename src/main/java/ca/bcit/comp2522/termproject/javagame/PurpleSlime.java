package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;
import java.util.Random;

public class PurpleSlime extends Slime {
    public static final String PURPLE_SLIME_IMAGE_NAME = "purpleSlime.png";
    public static final String PURPLE_SLIME_NAME = "Purple Slime";
    public PurpleSlime(double xPosition, double yPosition, PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        this.setPrice(new Random().nextInt(40,50));
    }

    @Override
    protected String setConstantName() {
        return PURPLE_SLIME_NAME;
    }

    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return null;
    }


    protected String getConstantSlimeImageName() {
        return PURPLE_SLIME_IMAGE_NAME;
    }
}
