package ca.bcit.comp2522.termproject.javagame;

public class SlimeCurrency {
    private int balance;

    public SlimeCurrency() {
        this.balance = 10;
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

    public int getBalance() {
        return this.balance;
    }

}
