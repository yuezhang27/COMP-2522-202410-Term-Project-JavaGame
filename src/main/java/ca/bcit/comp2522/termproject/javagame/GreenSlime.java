package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;

public class GreenSlime extends Slime{
    public static final String GREEN_SLIME_IMAGE_NAME = "greenSlime.png";
    public GreenSlime(double xPosition, double yPosition, PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
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
        return GREEN_SLIME_IMAGE_NAME;
    }
}
