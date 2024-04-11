import ca.bcit.comp2522.termproject.javagame.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void initialBalanceShouldBeTen() {
        assertEquals(10, player.getBalance());
    }

    @Test
    public void increaseBalanceShouldWorkCorrectly() {
        player.increaseBalance(5);
        assertEquals(15, player.getBalance());
    }

    @Test
    public void reduceBalanceShouldReturnTrueWhenSuccessful() {
        assertTrue(player.reduceBalance(5));
        assertEquals(5, player.getBalance());
    }

    @Test
    public void reduceBalanceShouldReturnFalseWhenOutcomeExceedsBalance() {
        assertFalse(player.reduceBalance(11));
        assertEquals(10, player.getBalance());
    }

    @Test
    public void getBalanceShouldReturnCurrentBalance() {
        assertEquals(10, player.getBalance());
        player.increaseBalance(20);
        assertEquals(30, player.getBalance());
        player.reduceBalance(10);
        assertEquals(20, player.getBalance());
    }
}

