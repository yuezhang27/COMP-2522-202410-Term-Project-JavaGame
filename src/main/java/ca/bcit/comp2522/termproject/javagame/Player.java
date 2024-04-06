package ca.bcit.comp2522.termproject.javagame;

/**
 * Represents a player with a balance.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class Player {
    private static final int INITIAL_BALANCE = 10;
    private int balance;

    /**
     * Constructs a new player with an initial balance of 10.
     */
    public Player() {
        this.balance = INITIAL_BALANCE;
    }

    /**
     * Increases the player's balance by the specified income.
     *
     * @param income the amount to increase the balance by
     */
    public void increaseBalance(final int income) {
        this.balance += income;
    }

    /**
     * Reduces the player's balance by the specified outcome.
     *
     * @param outcome the amount to reduce the balance by
     * @return true if the balance was successfully reduced, false if the outcome exceeds the current balance
     */
    public boolean reduceBalance(final int outcome) {
        if (outcome > this.balance) {
            return false;
        }
        this.balance -= outcome;
        return true;
    }

    /**
     * Gets the current balance of the player.
     *
     * @return the current balance
     */
    public int getBalance() {
        return this.balance;
    }
}
