import ca.bcit.comp2522.termproject.javagame.PetriDish;
import ca.bcit.comp2522.termproject.javagame.YellowSlime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class YellowSlimeTest {
    private YellowSlime yellowSlime;
    private PetriDish petriDish;

    @BeforeEach
    public void setUp() {
        PetriDish mockPetriDish = new PetriDish();
        yellowSlime = new YellowSlime(100, 100, mockPetriDish, true);
    }

    @Test
    public void containsConstantCalledINITIAL_RADIUS() {
        assertEquals(10, yellowSlime.INITIAL_RADIUS);
    }

    @Test
    public void containsConstantCalledINITIAL_SIZE() {
        assertEquals(1, yellowSlime.INITIAL_SIZE);
    }

    @Test
    public void containsConstantCalledMAX_VELOCITY() {
        assertEquals(6, yellowSlime.MAX_VELOCITY);
    }

    @Test
    void priceWithinRange() {
        assertTrue(yellowSlime.getPrice() <= YellowSlime.MAX_PRICE);
    }

    @Test
    public void testNoImageUnderTest() {
        assertNotNull(yellowSlime);
        assertNull(yellowSlime.getImageView());
    }

    @Test
    void nameAndImageSetCorrectly() {
        assertEquals(YellowSlime.YELLOW_SLIME_NAME, yellowSlime.getName());
        assertEquals(YellowSlime.YELLOW_SLIME_IMAGE_NAME, yellowSlime.getSlimeImage());
    }

    @Test
    void testSetPrice() {
        int newPrice = 100;
        yellowSlime.setPrice(newPrice);
        assertEquals(newPrice, yellowSlime.getPrice());
    }

    @Test
    void testSetAlive() {
        yellowSlime.setAlive(false);
        assertFalse(yellowSlime.isAlive());
    }

    @Test
    void slimeIdShouldBeUniqueAndIncremental() {
        int firstSlimeId = yellowSlime.getSlimeId();
        PetriDish mockPetriDish = new PetriDish();
        YellowSlime anotherSlime = new YellowSlime(100, 100, mockPetriDish, true);
        assertEquals(firstSlimeId + 1, anotherSlime.getSlimeId());
    }


    @Test
    void slimeMoveCorrectly() {
        double initialX = yellowSlime.getCenterX();
        double initialY = yellowSlime.getCenterY();
        double xVelocity = yellowSlime.getXVelocity();
        double yVelocity = yellowSlime.getYVelocity();
        yellowSlime.checkBounds();
        assertEquals(initialX + xVelocity, yellowSlime.getCenterX());
        assertEquals(initialY + yVelocity, yellowSlime.getCenterY());
    }

    @Test
    void slimeShouldGrowCorrectly() {
        double initialSize = yellowSlime.getRadius();
        yellowSlime.grow(2);
        assertEquals(initialSize + 2 * 2, yellowSlime.getRadius());
    }

    @Test
    void slimeIsNotAliveAfterDie() {
        yellowSlime.die();
        assertFalse(yellowSlime.isAlive());
    }

    @Test
    void slimeShouldDieCorrectly() {
        yellowSlime.die();
        assertFalse(yellowSlime.isAlive());
    }

    @Test
    void slimeShouldShrinkToBabyCorrectly() {
        yellowSlime.grow(5);
        yellowSlime.shrinkSlimeToBaby();
        assertEquals(yellowSlime.INITIAL_RADIUS, yellowSlime.getRadius());
    }

}
