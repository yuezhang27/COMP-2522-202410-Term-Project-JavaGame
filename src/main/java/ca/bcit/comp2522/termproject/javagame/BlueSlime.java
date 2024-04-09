package ca.bcit.comp2522.termproject.javagame;

import java.util.Random;

/**
 * BlueSlime class represents a type of Slime with blue color.
 * It extends the Slime class and provides specific behavior and properties for blue slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class BlueSlime extends Slime {
    /**
     * The image name for blue slime.
     */
    public static final String BLUE_SLIME_IMAGE_NAME = "blueSlime.png";
    /**
     * The name for blue slime.
     */
    public static final String BLUE_SLIME_NAME = "Blue Slime";
    /**
     * Represents the maximum price that can be assigned to a slime.
     */
    public static final int MAX_PRICE = 20;
    /**
     * Represents the minimum price that can be assigned to a slime.
     */
    public static final int MIN_PRICE = 11;

    /**
     * Constructs a new BlueSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     */
    public BlueSlime(final double xPosition, final double yPosition, final PetriDish petriDish, final boolean isTest) {
        super(xPosition, yPosition, petriDish, isTest);
        this.setPrice(new Random().nextInt(MIN_PRICE, MAX_PRICE));
    }
    /**
     * Constructs a new BlueSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     */
    public BlueSlime(final double xPosition, final double yPosition, final PetriDish petriDish) {
        this(xPosition, yPosition, petriDish, false);
    }

    /**
     * Sets the constant name for the blue slime.
     *
     * @return the constant name for the blue slime
     */
    @Override
    protected String setConstantName() {
        return BLUE_SLIME_NAME;
    }

    /**
     * Gets the constant image name for the blue slime.
     *
     * @return the constant image name for the blue slime
     */
    @Override
    protected String getConstantSlimeImageName() {
        return BLUE_SLIME_IMAGE_NAME;
    }


}
