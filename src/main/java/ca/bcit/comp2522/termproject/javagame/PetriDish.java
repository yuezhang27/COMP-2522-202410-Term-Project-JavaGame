package ca.bcit.comp2522.termproject.javagame;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Represents a petri dish in the game.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class PetriDish {
    /**
     * The image file path for the petri dish.
     */
    public static final String PETRI_DISH_IMAGE = "regularPetriDish.png";
    private final ArrayList<Slime> slimesList;
    private final ArrayList<Slime> deadSlimeList;
    private final ArrayList<Thread> threadList;
    private boolean isStopThread = false;
    private final Pane canvas;
    private Slime defaultSlime = null;

    /**
     * Constructs a new PetriDish object with empty lists and a new canvas.
     */
    public PetriDish() {
        this.slimesList = new ArrayList<>();
        this.threadList = new ArrayList<>();
        this.deadSlimeList = new ArrayList<>();
        this.canvas = new Pane();
    }

    /**
     * Adds a slime to the petri dish.
     *
     * @param slime The slime to add.
     */
    public void addSlime(final Slime slime) {
        if (slime != null) {
            this.slimesList.add(slime);
            this.threadList.add(slime.getThread());
        }
    }

    /**
     * Removes a slime from the petri dish.
     *
     * @param slime The slime to remove.
     */
    public void removeSlime(final Slime slime) {
        if (slime != null && !this.slimesList.isEmpty()) {
            slime.stopThread();
            this.slimesList.remove(slime);
            this.threadList.remove(slime.getThread());
        }
    }

    /**
     * Checks the slime count and kills excess slimes.
     */
    public void checkSlimeCountAndKill() {
        final int maxSlimes = 10;
        if (slimesList.size() > maxSlimes) {
            int count = slimesList.size() - maxSlimes;
            for (int i = 0; i < count; i++) {
                Slime slime = slimesList.get(i);
                slime.die();
                deadSlimeList.add(slime);
                removeSlime(slime);
            }
        }
    }

    /**
     * Gets the list of living slimes in the petri dish.
     *
     * @return The list of living slimes.
     */
    public ArrayList<Slime> getSlimesList() {
        return this.slimesList;
    }

    /**
     * Gets the list of dead slimes in the petri dish.
     *
     * @return The list of dead slimes.
     */
    public ArrayList<Slime> getDeadSlimeList() {
        return this.deadSlimeList;
    }

    /**
     * Gets the list of threads associated with the slimes.
     *
     * @return The list of threads.
     */
    public ArrayList<Thread> getThreadList() {
        return this.threadList;
    }

    //setters
    /**
     * Sets the stop thread status.
     *
     * @param newStopThread The new stop thread status.
     */
    public void setStopThread(final boolean newStopThread) {
        this.isStopThread = newStopThread;
    }

    /**
     * Gets the canvas representing the petri dish.
     *
     * @return The canvas.
     */
    public Pane getCanvas() {
        return this.canvas;
    }

    /**
     * Gets the default slime in the petri dish.
     *
     * @return The default slime.
     */
    public Slime getDefaultSlime() {
        return this.defaultSlime;
    }

    /**
     * Gets the status of the stop thread flag.
     *
     * @return The status of the stop thread flag.
     */
    public boolean getIsStopThread() {
        return this.isStopThread;
    }

    /**
     * Removes a thread from the list of threads.
     *
     * @param thread The thread to remove.
     */
    public void removeThread(final Thread thread) {
        threadList.remove(thread);
    }

    /**
     * Sets the default slime in the petri dish.
     *
     * @param slime The default slime.
     */
    public void setDefaultSlime(final Slime slime) {
        this.defaultSlime = slime;
    }


}
