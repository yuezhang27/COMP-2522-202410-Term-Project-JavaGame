package ca.bcit.comp2522.termproject.javagame;

public abstract class Slime {
    protected int size;

    protected abstract SlimeType setConstantSlimeType(SlimeType slimeType);
    protected abstract int setConstantSize(int size);
    protected abstract int setConstantPrice(int size);
    protected abstract void setSize(int newSize);
}
