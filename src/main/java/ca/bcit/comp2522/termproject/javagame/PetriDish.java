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
    private boolean isStopThread = false;
    private final Pane canvas = new Pane();


    public PetriDish() {
        this.slimesList = new ArrayList<>();
        this.threadList = new ArrayList<>();
        this.mutationProbability = 0.1;
        this.growSpeed = 1.0;
        this.petriDishPrice = 10;
        this.petriDishLevel = 1;
    }

    public boolean upgradePetrDish(Player player, int cost) {
        if (this.petriDishLevel == 3) {
            return false;
        }
        player.reduceBalance(cost);
        this.petriDishLevel +=1;
        this.growSpeed += 0.5;
        this.mutationProbability += 0.2;
        return true;

    }

    public void addSlime(Slime slime) {
        if (slime != null) {
            this.slimesList.add(slime);
            this.threadList.add(slime.getThread());
        }
    }

    public void removeSlime(Slime slime){
        if (slime != null && !this.slimesList.isEmpty()) {
            slime.stopThread();
            this.slimesList.remove(slime);
            this.threadList.remove(slime.getThread());
//            this.getCanvas().getChildren().remove(slime.getImageView());
        }
    }
    public void checkSlimeCountAndKill() {
        if (slimesList.size() > 10) {
            int count = slimesList.size() - 10;
            for (int i = 0; i < count; i++) {
                Slime slime = slimesList.get(i);
                slime.die();
                removeSlime(slime);
            }
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
    public ArrayList<Slime> getSlimesList() {
        return slimesList;
    }

    //setters
    public void setMutationProbability(double newMutationProbability) {
        this.mutationProbability = newMutationProbability;
    }

    public void setStopThread(boolean newStopThread) {
        this.isStopThread = newStopThread;
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
    public boolean getIsStopThread(){return this.isStopThread;}
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
