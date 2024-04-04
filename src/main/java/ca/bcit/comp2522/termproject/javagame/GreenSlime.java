package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;
import java.util.Random;

public class GreenSlime extends Slime{
    public static final String GREEN_SLIME_IMAGE_NAME = "greenSlime.png";
    public static final String GREEN_SLIME_NAME = "Green Slime";
    public GreenSlime(double xPosition, double yPosition, PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        //绿色：20-29
        this.setPrice(new Random().nextInt(20,30));
    }

    @Override
    protected String setConstantName() {
        return GREEN_SLIME_NAME;
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
