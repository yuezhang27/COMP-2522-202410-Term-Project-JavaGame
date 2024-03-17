package ca.bcit.comp2522.termproject.javagame;

import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Currency;

public class PetriDish {
    private final ArrayList<Slime> slimesList ;
    private final ArrayList<Thread> threadList;
    private double mutationProbability;
    private double growSpeed;
    private int petriDishPrice;
    private int petriDishLevel;
    private final SlimeCurrency slimeCurrency = new SlimeCurrency();
    private final Pane canvas = new Pane();


    public PetriDish() {
        this.slimesList = new ArrayList<>();
        this.threadList = new ArrayList<>();
        this.mutationProbability = 0.1;
        this.growSpeed = 1.0;
        this.petriDishPrice = 10;
        this.petriDishLevel = 1;
    }

    public boolean upgradePetrDish(Currency currency, int cost) {
        if (this.petriDishLevel == 3) {
            return false;
        }
        slimeCurrency.reduceBalance(cost);
        this.petriDishLevel +=1;
        this.growSpeed += 0.5;
        this.mutationProbability += 0.2;
        return true;

    }

    public boolean addSlime(Slime slime) {
        if (slime == null) {
            return false;
        } else {
            this.slimesList.add(slime);
            return true;
        }
    }

    public boolean removeSlime(Slime slime){
        if (slime == null || this.slimesList.isEmpty()) {
            return false;
        } else {
            this.slimesList.remove(slime);
            return true;
        }
    }

    //getters
    public double getMutationProbability() {
        return this.mutationProbability;
    }

    public double getGrowSpeed() {
        return this.growSpeed;
    }

    public int getPetriDishPrice() {
        return this.petriDishPrice;
    }

    public int getPetriDishLevel() {
        return this.petriDishLevel;
    }

    //setters
    public void setMutationProbability(double newMutationProbability) {
        this.mutationProbability = newMutationProbability;
    }

    public void setGrowSpeed(double newGrowSpeed) {
        this.growSpeed = newGrowSpeed;
    }

    public void setPetriDishPrice(int newPetriDishPrice) {
        this.petriDishPrice = newPetriDishPrice;
    }

    public void setPetriDishLevel(int newPetriDishLevel) {
        this.petriDishLevel = newPetriDishLevel;
    }
    public Pane getCanvas(){
        return this.canvas;
    }
    public void addThread(Thread thread) {
        threadList.add(thread);
    }

    public void removeThread(Thread thread) {
        threadList.remove(thread);
    }

    public ArrayList<Thread> getThreadList() {
        return threadList;
    }


}
