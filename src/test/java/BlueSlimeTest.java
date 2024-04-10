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
    public void containsConstantCalledINITIAL_RADIUS() {
        assertEquals(10, blueSlime.INITIAL_RADIUS);
    }

    @Test
    public void containsConstantCalledINITIAL_SIZE() {
        assertEquals(1, blueSlime.INITIAL_SIZE);
    }

    @Test
    public void containsConstantCalledMAX_VELOCITY() {
        assertEquals(6, blueSlime.MAX_VELOCITY);
    }

    @Test
    void priceWithinRange() {
        assertTrue(blueSlime.getPrice() >= BlueSlime.MIN_PRICE && blueSlime.getPrice() <= BlueSlime.MAX_PRICE);
    }

    @Test
    public void testNoImageUnderTest() {
        assertNotNull(blueSlime);
        assertNull(blueSlime.getImageView());
    }

    @Test
    void nameAndImageSetCorrectly() {
        assertEquals(BlueSlime.BLUE_SLIME_NAME, blueSlime.getName());
        assertEquals(BlueSlime.BLUE_SLIME_IMAGE_NAME, blueSlime.getSlimeImage());
    }

    @Test
    void testSetPrice() {
        int newPrice = 100;
        blueSlime.setPrice(newPrice);
        assertEquals(newPrice, blueSlime.getPrice());
    }

    @Test
    void testSetAlive() {
        blueSlime.setAlive(false);
        assertFalse(blueSlime.isAlive());
    }

    @Test
    void slimeIdShouldBeUniqueAndIncremental() {
        int firstSlimeId = blueSlime.getSlimeId();
        PetriDish mockPetriDish = new PetriDish();
        BlueSlime anotherSlime = new BlueSlime(100, 100, mockPetriDish, true);
        assertEquals(firstSlimeId + 1, anotherSlime.getSlimeId());
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
    void slimeShouldGrowCorrectly() {
        double initialSize = blueSlime.getRadius();
        blueSlime.grow(2);
        assertEquals(initialSize + 2 * 2, blueSlime.getRadius());
    }

    @Test
    void slimeIsNotAliveAfterDie() {
        blueSlime.die();
        assertFalse(blueSlime.isAlive());
    }

    @Test
    void slimeShouldDieCorrectly() {
        blueSlime.die();
        assertFalse(blueSlime.isAlive());
    }

    @Test
    void slimeShouldShrinkToBabyCorrectly() {
        blueSlime.grow(5);
        blueSlime.shrinkSlimeToBaby();
        assertEquals(blueSlime.INITIAL_RADIUS, blueSlime.getRadius());
    }

}
