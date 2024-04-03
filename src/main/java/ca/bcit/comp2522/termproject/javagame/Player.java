package ca.bcit.comp2522.termproject.javagame;

public class Player {
    private int balance;

    public Player() {
        this.balance = 0;

    }
    public void increaseBalance(int income) {
        this.balance += income;
    }

    public boolean reduceBalance(int outcome) {
        if (outcome > this.balance) {
            return false;
        }
        this.balance -= outcome;
        return true;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }
}
