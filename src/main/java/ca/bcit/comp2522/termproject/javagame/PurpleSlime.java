package ca.bcit.comp2522.termproject.javagame;

import java.util.Random;

/**
 * PurpleSlime class represents a type of Slime with purple color.
 * It extends the Slime class and provides specific behavior and properties for purple slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class PurpleSlime extends Slime {
    /**
     * The image name for purple slime.
     */
    public static final String PURPLE_SLIME_IMAGE_NAME = "purpleSlime.png";
    /**
     * The name for purple slime.
     */
    public static final String PURPLE_SLIME_NAME = "Purple Slime";
    /**
     * Represents the maximum price that can be assigned to a slime.
     */
    public static final int MAX_PRICE = 50;
    /**
     * Represents the minimum price that can be assigned to a slime.
     */
    public static final int MIN_PRICE = 41;

    /**
     * Constructs a new PurpleSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     */
    public PurpleSlime(final double xPosition, final double yPosition, final PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        this.setPrice(new Random().nextInt(MIN_PRICE, MAX_PRICE));
    }

    /**
     * Sets the constant name for the purple slime.
     *
     * @return the constant name for the purple slime
     */
    @Override
    protected String setConstantName() {
        return PURPLE_SLIME_NAME;
    }

    /**
     * Gets the constant image name for the purple slime.
     *
     * @return the constant image name for the purple slime
     */
    protected String getConstantSlimeImageName() {
        return PURPLE_SLIME_IMAGE_NAME;
    }
}
