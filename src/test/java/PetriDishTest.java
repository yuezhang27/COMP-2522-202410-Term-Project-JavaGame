import ca.bcit.comp2522.termproject.javagame.BlueSlime;
import ca.bcit.comp2522.termproject.javagame.PetriDish;
import ca.bcit.comp2522.termproject.javagame.Slime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetriDishTest {

    private PetriDish petriDish;
    private Slime testSlime;

    @BeforeEach
    public void setUp() {
        petriDish = new PetriDish();
        testSlime = new BlueSlime(100, 100, petriDish, true);
    }

    @Test
    public void addSlimeIncreasesListSize() {
        int initialSize = petriDish.getSlimesList().size();
        petriDish.addSlime(testSlime);
        assertEquals(initialSize + 1, petriDish.getSlimesList().size());
    }

    @Test
    public void removeSlimeDecreasesListSize() {
        petriDish.addSlime(testSlime);
        int sizeAfterAddition = petriDish.getSlimesList().size();
        petriDish.removeSlime(testSlime);
        assertEquals(sizeAfterAddition - 1, petriDish.getSlimesList().size());
    }

    @Test
    public void checkSlimeCountAndKillRemovesExcessSlimes() {
        for (int i = 0; i < 12; i++) {
            petriDish.addSlime(new BlueSlime(100, 100, petriDish, true));
        }
        petriDish.checkSlimeCountAndKill();
        assertEquals(10, petriDish.getSlimesList().size());
    }

    @Test
    public void setStopThreadCorrectlyUpdatesFlag() {
        petriDish.setStopThread(true);
        assertTrue(petriDish.getIsStopThread());
    }

    @Test
    public void getCanvasReturnsNonNullValue() {
        assertNotNull(petriDish.getCanvas());
    }

    @Test
    public void getDefaultSlimeInitiallyReturnsNull() {
        assertNull(petriDish.getDefaultSlime());
    }

    @Test
    public void setDefaultSlimeCorrectlySetsSlime() {
        petriDish.setDefaultSlime(testSlime);
        assertEquals(testSlime, petriDish.getDefaultSlime());
    }

    @Test
    public void removeThreadDecreasesThreadListSize() {
        petriDish.addSlime(testSlime);
        int initialSize = petriDish.getThreadList().size();
        petriDish.removeThread(testSlime.getThread());
        assertEquals(initialSize - 1, petriDish.getThreadList().size());
    }
}
