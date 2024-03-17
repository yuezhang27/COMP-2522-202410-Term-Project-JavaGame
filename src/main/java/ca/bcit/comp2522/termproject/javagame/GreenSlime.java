package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;

public class GreenSlime extends Slime{
    public GreenSlime(double xPosition, double yPosition) {
        super(xPosition, yPosition);
    }

    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return null;
    }

    @Override
    protected String setConstantSlimeImage(String imgName) {
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
}
