package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;
import java.util.Random;

public class PurpleSlime extends Slime {
    public static final String PURPLE_SLIME_IMAGE_NAME = "purpleSlime.png";
    public PurpleSlime(double xPosition, double yPosition, PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        this.setPrice(new Random().nextInt(40,50));
    }

    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return null;
    }


    @Override
    protected void moveSlime(Slime slime) {

    }

    @Override
    protected ArrayList<Slime> splitSlime(Slime slime) {
        return null;
    }

    @Override
    protected boolean checkIsCollide() {
        return false;
    }

    @Override
    protected Slime slimeMutation() {
        return null;
    }
    protected String getConstantSlimeImageName() {
        return PURPLE_SLIME_IMAGE_NAME;
    }
}
