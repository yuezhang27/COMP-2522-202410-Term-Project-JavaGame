package ca.bcit.comp2522.termproject.javagame;

import java.util.ArrayList;

public abstract class Slime {
    private int size;
    private double xCoordinator;
    private double yCoordinator;
    private double xVelocity;
    private double yVelocity;
    private int price;
    private boolean isAlive;
    public int getSize() {
        return this.size;
    }

    public double getXCoordinator() {
        return this.xCoordinator;
    }

    public void setXCoordinator(double newXCoordinator) {
        this.xCoordinator = newXCoordinator;
    }

    public double getYCoordinator() {
        return this.yCoordinator;
    }

    public void setYCoordinator(double newYCoordinator) {
        this.yCoordinator = newYCoordinator;
    }

    public double getXVelocity() {
        return this.xVelocity;
    }

    public void setXVelocity(double newXVelocity) {
        this.xVelocity = newXVelocity;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    public void setYVelocity(double newYVelocity) {
        this.yVelocity = newYVelocity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setAlive(boolean newIsAlive) {
        if (this.isAlive) {
            this.isAlive = newIsAlive;
        }
    }



    protected abstract SlimeType setConstantSlimeType(SlimeType slimeType);
    protected abstract String setConstantSlimeImage(String imgName);
    protected abstract void moveSlime(Slime slime);

    protected abstract ArrayList<Slime> splitSlime(Slime slime);
    protected abstract boolean checkIsCollide();
    protected abstract Slime slimeMutation();


}
