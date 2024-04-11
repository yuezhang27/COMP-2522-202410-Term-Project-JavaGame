import ca.bcit.comp2522.termproject.javagame.PetriDish;
import ca.bcit.comp2522.termproject.javagame.PurpleSlime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurpleSlimeTest {
    private PurpleSlime purpleSlime;
    private PetriDish petriDish;

    @BeforeEach
    public void setUp() {
        PetriDish mockPetriDish = new PetriDish();
        purpleSlime = new PurpleSlime(100, 100, mockPetriDish, true);
    }

    @Test
    public void containsConstantCalledINITIAL_RADIUS() {
        assertEquals(10, purpleSlime.INITIAL_RADIUS);
    }

    @Test
    public void containsConstantCalledINITIAL_SIZE() {
        assertEquals(1, purpleSlime.INITIAL_SIZE);
    }

    @Test
    public void containsConstantCalledMAX_VELOCITY() {
        assertEquals(6, purpleSlime.MAX_VELOCITY);
    }

    @Test
    void priceWithinRange() {
        assertTrue(purpleSlime.getPrice() >= PurpleSlime.MIN_PRICE && purpleSlime.getPrice() <= PurpleSlime.MAX_PRICE);
    }

    @Test
    public void testNoImageUnderTest() {
        assertNotNull(purpleSlime);
        assertNull(purpleSlime.getImageView());
    }

    @Test
    void nameAndImageSetCorrectly() {
        assertEquals(PurpleSlime.PURPLE_SLIME_NAME, purpleSlime.getName());
        assertEquals(PurpleSlime.PURPLE_SLIME_IMAGE_NAME, purpleSlime.getSlimeImage());
    }

    @Test
    void testSetPrice() {
        int newPrice = 100;
        purpleSlime.setPrice(newPrice);
        assertEquals(newPrice, purpleSlime.getPrice());
    }

    @Test
    void testSetAlive() {
        purpleSlime.setAlive(false);
        assertFalse(purpleSlime.isAlive());
    }

    @Test
    void slimeIdShouldBeUniqueAndIncremental() {
        int firstSlimeId = purpleSlime.getSlimeId();
        PetriDish mockPetriDish = new PetriDish();
        PurpleSlime anotherSlime = new PurpleSlime(100, 100, mockPetriDish, true);
        assertEquals(firstSlimeId + 1, anotherSlime.getSlimeId());
    }


    @Test
    void slimeMoveCorrectly() {
        double initialX = purpleSlime.getCenterX();
        double initialY = purpleSlime.getCenterY();
        double xVelocity = purpleSlime.getXVelocity();
        double yVelocity = purpleSlime.getYVelocity();
        purpleSlime.checkBounds();
        assertEquals(initialX + xVelocity, purpleSlime.getCenterX());
        assertEquals(initialY + yVelocity, purpleSlime.getCenterY());
    }

    @Test
    void slimeShouldGrowCorrectly() {
        double initialSize = purpleSlime.getRadius();
        purpleSlime.grow(2);
        assertEquals(initialSize + 2 * 2, purpleSlime.getRadius());
    }

    @Test
    void slimeIsNotAliveAfterDie() {
        purpleSlime.die();
        assertFalse(purpleSlime.isAlive());
    }

    @Test
    void slimeShouldDieCorrectly() {
        purpleSlime.die();
        assertFalse(purpleSlime.isAlive());
    }

    @Test
    void slimeShouldShrinkToBabyCorrectly() {
        purpleSlime.grow(5);
        purpleSlime.shrinkSlimeToBaby();
        assertEquals(purpleSlime.INITIAL_RADIUS, purpleSlime.getRadius());
    }

}
