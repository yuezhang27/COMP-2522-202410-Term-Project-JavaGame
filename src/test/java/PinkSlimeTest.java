import ca.bcit.comp2522.termproject.javagame.BlueSlime;
import ca.bcit.comp2522.termproject.javagame.PetriDish;
import ca.bcit.comp2522.termproject.javagame.PinkSlime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PinkSlimeTest {
    private PinkSlime pinkSlime;
    private PetriDish petriDish;

    @BeforeEach
    public void setUp() {
        PetriDish mockPetriDish = new PetriDish();
        pinkSlime = new PinkSlime(100, 100, mockPetriDish, true);
    }

    @Test
    public void containsConstantCalledINITIAL_RADIUS() {
        assertEquals(10, pinkSlime.INITIAL_RADIUS);
    }

    @Test
    public void containsConstantCalledINITIAL_SIZE() {
        assertEquals(1, pinkSlime.INITIAL_SIZE);
    }

    @Test
    public void containsConstantCalledMAX_VELOCITY() {
        assertEquals(6, pinkSlime.MAX_VELOCITY);
    }

    @Test
    void priceWithinRange() {
        assertTrue(pinkSlime.getPrice() >= PinkSlime.MIN_PRICE && pinkSlime.getPrice() <= PinkSlime.MAX_PRICE);
    }

    @Test
    public void testNoImageUnderTest() {
        assertNotNull(pinkSlime);
        assertNull(pinkSlime.getImageView());
    }

    @Test
    void nameAndImageSetCorrectly() {
        assertEquals(PinkSlime.PINK_SLIME_NAME, pinkSlime.getName());
        assertEquals(PinkSlime.PINK_SLIME_IMAGE_NAME, pinkSlime.getSlimeImage());
    }

    @Test
    void testSetPrice() {
        int newPrice = 100;
        pinkSlime.setPrice(newPrice);
        assertEquals(newPrice, pinkSlime.getPrice());
    }

    @Test
    void testSetAlive() {
        pinkSlime.setAlive(false);
        assertFalse(pinkSlime.isAlive());
    }

    @Test
    void slimeIdShouldBeUniqueAndIncremental() {
        int firstSlimeId = pinkSlime.getSlimeId();
        PetriDish mockPetriDish = new PetriDish();
        PinkSlime anotherSlime = new PinkSlime(100, 100, mockPetriDish, true);
        assertEquals(firstSlimeId + 1, anotherSlime.getSlimeId());
    }


    @Test
    void slimeMoveCorrectly() {
        double initialX = pinkSlime.getCenterX();
        double initialY = pinkSlime.getCenterY();
        double xVelocity = pinkSlime.getXVelocity();
        double yVelocity = pinkSlime.getYVelocity();
        pinkSlime.checkBounds();
        assertEquals(initialX + xVelocity, pinkSlime.getCenterX());
        assertEquals(initialY + yVelocity, pinkSlime.getCenterY());
    }

    @Test
    void slimeShouldGrowCorrectly() {
        double initialSize = pinkSlime.getRadius();
        pinkSlime.grow(2);
        assertEquals(initialSize + 2 * 2, pinkSlime.getRadius());
    }

    @Test
    void slimeIsNotAliveAfterDie() {
        pinkSlime.die();
        assertFalse(pinkSlime.isAlive());
    }

    @Test
    void slimeShouldDieCorrectly() {
        pinkSlime.die();
        assertFalse(pinkSlime.isAlive());
    }

    @Test
    void slimeShouldShrinkToBabyCorrectly() {
        pinkSlime.grow(5);
        pinkSlime.shrinkSlimeToBaby();
        assertEquals(pinkSlime.INITIAL_RADIUS, pinkSlime.getRadius());
    }

}
