package ca.bcit.comp2522.termproject.javagame;

import java.util.Random;

/**
 * YellowSlime class represents a type of Slime with yellow color.
 * It extends the Slime class and provides specific behavior and properties for yellow slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class YellowSlime extends Slime {
    /**
     * The image name for yellow slime.
     */
    public static final String YELLOW_SLIME_IMAGE_NAME = "yellowSlime.png";
    /**
     * The name for yellow slime.
     */
    public static final String YELLOW_SLIME_NAME = "Yellow Slime";
    /**
     * Represents the maximum price that can be assigned to a slime.
     */
    public static final int MAX_PRICE = 10;

    /**
     * Constructs a new YellowSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     * @param test the boolean value representing if the instance created is for test
     */
    public YellowSlime(final double xPosition, final double yPosition, final PetriDish petriDish, final boolean test) {
        super(xPosition, yPosition, petriDish, test);
        this.setPrice(new Random().nextInt(MAX_PRICE));
    }

    /**
     * Constructs a new YellowSlime object with the specified position and PetriDish.
     *
     * @param xPosition the x-coordinate position of the slime
     * @param yPosition the y-coordinate position of the slime
     * @param petriDish the PetriDish where the slime belongs
     */
    public YellowSlime(final double xPosition, final double yPosition, final PetriDish petriDish) {
        this(xPosition, yPosition, petriDish, false);
    }

    /**
     * Sets the constant name for the yellow slime.
     *
     * @return the constant name for the yellow slime
     */
    @Override
    protected String setConstantName() {
        return YELLOW_SLIME_NAME;
    }

    /**
     * Gets the constant image name for the yellow slime.
     *
     * @return the constant image name for the yellow slime
     */
    @Override
    protected String getConstantSlimeImageName() {
        return YELLOW_SLIME_IMAGE_NAME;
    }


}
