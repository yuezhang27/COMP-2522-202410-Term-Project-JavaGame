package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;
import java.util.Random;

public class PinkSlime extends Slime{
    public static final String PINK_SLIME_IMAGE_NAME = "pinkSlime.png";
    public PinkSlime(double xPosition, double yPosition, PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        //粉色：30-39
        this.setPrice(new Random().nextInt(30,40));
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

    @Override
    protected String getConstantSlimeImageName() {
        return PINK_SLIME_IMAGE_NAME;
    }
}
