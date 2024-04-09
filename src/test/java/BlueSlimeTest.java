import static org.junit.jupiter.api.Assertions.*;

import ca.bcit.comp2522.termproject.javagame.BlueSlime;
import ca.bcit.comp2522.termproject.javagame.PetriDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlueSlimeTest {
    private BlueSlime blueSlime;
    private PetriDish petriDish;

    @BeforeEach
    public void setUp() {
        PetriDish mockPetriDish = new PetriDish();
        blueSlime = new BlueSlime(100, 100, mockPetriDish, true);
    }

    @Test
    public void testNoImageUnderTest() {
        assertNotNull(blueSlime);
        assertNull(blueSlime.getImageView());
    }

    @Test
    public void testPriceIsWithinRange() {
        assertTrue(blueSlime.getPrice() >= BlueSlime.MIN_PRICE && blueSlime.getPrice() < BlueSlime.MAX_PRICE);
    }

    @Test
    public void testNameAndImageName() {
        assertEquals(BlueSlime.BLUE_SLIME_NAME, blueSlime.getName());
        assertEquals(BlueSlime.BLUE_SLIME_IMAGE_NAME, blueSlime.getSlimeImage());
    }

    @Test
    void slimeMoveCorrectly() {
        double initialX = blueSlime.getCenterX();
        double initialY = blueSlime.getCenterY();
        double xVelocity = blueSlime.getXVelocity();
        double yVelocity = blueSlime.getYVelocity();
        blueSlime.checkBounds();
        assertEquals(initialX + xVelocity, blueSlime.getCenterX());
        assertEquals(initialY + yVelocity, blueSlime.getCenterY());
    }

    @Test
    void priceWithinRange() {
        assertTrue(blueSlime.getPrice() >= BlueSlime.MIN_PRICE && blueSlime.getPrice() <= BlueSlime.MAX_PRICE);
    }

    @Test
    void nameAndImageSetCorrectly() {
        assertEquals(BlueSlime.BLUE_SLIME_NAME, blueSlime.getName());
        assertEquals(BlueSlime.BLUE_SLIME_IMAGE_NAME, blueSlime.getSlimeImage());
    }
    @Test
    void slimeShouldGrowCorrectly() {
        double initialSize = blueSlime.getRadius();
        blueSlime.grow(2);
        assertEquals(initialSize + 2 * 2, blueSlime.getRadius());
    }

    @Test
    void slimeShouldDieCorrectly() {
        blueSlime.die();
        assertFalse(blueSlime.isAlive());
    }

}
