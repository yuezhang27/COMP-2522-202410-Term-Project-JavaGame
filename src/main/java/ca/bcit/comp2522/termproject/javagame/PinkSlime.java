package ca.bcit.comp2522.termproject.javagame;

import java.util.Random;

/**
 * PinkSlime class represents a type of Slime with pink color.
 * It extends the Slime class and provides specific behavior and properties for pink slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class PinkSlime extends Slime {
    /**
     * The image name for pink slime.
     */
    public static final String PINK_SLIME_IMAGE_NAME = "pinkSlime.png";
    /**
     * The name for pink slime.
     */
    public static final String PINK_SLIME_NAME = "Pink Slime";
    /**
     * Represents the maximum price that can be assigned to a slime.
     */
    public static final int MAX_PRICE = 40;
    /**
     * Represents the minimum price that can be assigned to a slime.
     */
    public static final int MIN_PRICE = 31;

    /**
     * Constructs a new PinkSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     */
    public PinkSlime(final double xPosition, final double yPosition, final PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        this.setPrice(new Random().nextInt(MIN_PRICE, MAX_PRICE));
    }

    /**
     * Sets the constant name for the pink slime.
     *
     * @return the constant name for the pink slime
     */
    @Override
    protected String setConstantName() {
        return PINK_SLIME_NAME;
    }

    /**
     * Gets the constant image name for the pink slime.
     *
     * @return the constant image name for the pink slime
     */
    @Override
    protected String getConstantSlimeImageName() {
        return PINK_SLIME_IMAGE_NAME;
    }
}
