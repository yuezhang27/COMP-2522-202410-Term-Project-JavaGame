package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;

public class YellowSlime extends Slime{
    public static final SlimeType SLIME_TYPE = SlimeType.YELLOW_SLIME;
    public static final String SLIME_IMAGE = "yellowSlime.png";
    public static final int INITIAL_SIZE = 50;

    public YellowSlime(){
        super();
    }
    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return SLIME_TYPE;
    }

    @Override
    protected String setConstantSlimeImage(String imgName) {
        return SLIME_IMAGE;
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
