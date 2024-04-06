package ca.bcit.comp2522.termproject.javagame;

import java.util.Random;

/**
 * GreenSlime class represents a type of Slime with green color.
 * It extends the Slime class and provides specific behavior and properties for green slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class GreenSlime extends Slime {
    /**
     * The image name for green slime.
     */
    public static final String GREEN_SLIME_IMAGE_NAME = "greenSlime.png";
    /**
     * The name for green slime.
     */
    public static final String GREEN_SLIME_NAME = "Green Slime";
    /**
     * Represents the maximum price that can be assigned to a slime.
     */
    public static final int MAX_PRICE = 30;
    /**
     * Represents the minimum price that can be assigned to a slime.
     */
    public static final int MIN_PRICE = 21;

    /**
     * Constructs a new GreenSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     */
    public GreenSlime(final double xPosition, final double yPosition, final PetriDish petriDish) {
        super(xPosition, yPosition, petriDish);
        this.setPrice(new Random().nextInt(MIN_PRICE, MAX_PRICE));
    }

    /**
     * Sets the constant name for the green slime.
     *
     * @return the constant name for the green slime
     */
    @Override
    protected String setConstantName() {
        return GREEN_SLIME_NAME;
    }

    /**
     * Gets the constant image name for the green slime.
     *
     * @return the constant image name for the green slime
     */
    @Override
    protected String getConstantSlimeImageName() {
        return GREEN_SLIME_IMAGE_NAME;
    }

}
