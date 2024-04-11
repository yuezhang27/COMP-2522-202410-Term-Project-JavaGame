import ca.bcit.comp2522.termproject.javagame.BlueSlime;
import ca.bcit.comp2522.termproject.javagame.GreenSlime;
import ca.bcit.comp2522.termproject.javagame.PetriDish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GreenSlimeTest {
    private GreenSlime greenSlime;
    private PetriDish petriDish;

    @BeforeEach
    public void setUp() {
        PetriDish mockPetriDish = new PetriDish();
        greenSlime = new GreenSlime(100, 100, mockPetriDish, true);
    }

    @Test
    public void containsConstantCalledINITIAL_RADIUS() {
        assertEquals(10, greenSlime.INITIAL_RADIUS);
    }

    @Test
    public void containsConstantCalledINITIAL_SIZE() {
        assertEquals(1, greenSlime.INITIAL_SIZE);
    }

    @Test
    public void containsConstantCalledMAX_VELOCITY() {
        assertEquals(6, greenSlime.MAX_VELOCITY);
    }

    @Test
    void priceWithinRange() {
        assertTrue(greenSlime.getPrice() >= GreenSlime.MIN_PRICE && greenSlime.getPrice() <= GreenSlime.MAX_PRICE);
    }

    @Test
    public void testNoImageUnderTest() {
        assertNotNull(greenSlime);
        assertNull(greenSlime.getImageView());
    }

    @Test
    void nameAndImageSetCorrectly() {
        assertEquals(GreenSlime.GREEN_SLIME_NAME, greenSlime.getName());
        assertEquals(GreenSlime.GREEN_SLIME_IMAGE_NAME, greenSlime.getSlimeImage());
    }

    @Test
    void testSetPrice() {
        int newPrice = 100;
        greenSlime.setPrice(newPrice);
        assertEquals(newPrice, greenSlime.getPrice());
    }

    @Test
    void testSetAlive() {
        greenSlime.setAlive(false);
        assertFalse(greenSlime.isAlive());
    }

    @Test
    void slimeIdShouldBeUniqueAndIncremental() {
        int firstSlimeId = greenSlime.getSlimeId();
        PetriDish mockPetriDish = new PetriDish();
        GreenSlime anotherSlime = new GreenSlime(100, 100, mockPetriDish, true);
        assertEquals(firstSlimeId + 1, anotherSlime.getSlimeId());
    }


    @Test
    void slimeMoveCorrectly() {
        double initialX = greenSlime.getCenterX();
        double initialY = greenSlime.getCenterY();
        double xVelocity = greenSlime.getXVelocity();
        double yVelocity = greenSlime.getYVelocity();
        greenSlime.checkBounds();
        assertEquals(initialX + xVelocity, greenSlime.getCenterX());
        assertEquals(initialY + yVelocity, greenSlime.getCenterY());
    }

    @Test
    void slimeShouldGrowCorrectly() {
        double initialSize = greenSlime.getRadius();
        greenSlime.grow(2);
        assertEquals(initialSize + 2 * 2, greenSlime.getRadius());
    }

    @Test
    void slimeIsNotAliveAfterDie() {
        greenSlime.die();
        assertFalse(greenSlime.isAlive());
    }

    @Test
    void slimeShouldDieCorrectly() {
        greenSlime.die();
        assertFalse(greenSlime.isAlive());
    }

    @Test
    void slimeShouldShrinkToBabyCorrectly() {
        greenSlime.grow(5);
        greenSlime.shrinkSlimeToBaby();
        assertEquals(greenSlime.INITIAL_RADIUS, greenSlime.getRadius());
    }

}
