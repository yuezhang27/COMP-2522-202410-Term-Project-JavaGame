package ca.bcit.comp2522.termproject.javagame;

public class YellowSlime extends Slime{
    public static final SlimeType SLIME_TYPE = SlimeType.YELLOW_SLIME;
    public static final int INITIAL_SIZE = 50;

    public YellowSlime(){
        this.size = INITIAL_SIZE;
    }
    @Override
    protected SlimeType setConstantSlimeType(SlimeType slimeType) {
        return SLIME_TYPE;
    }

    @Override
    protected int setConstantSize(int size) {
        return 0;
    }

    @Override
    protected int setConstantPrice(int size) {
        return 0;
    }

    @Override
    protected void setSize(int newSize) {
        this.size = newSize;
    }
}
