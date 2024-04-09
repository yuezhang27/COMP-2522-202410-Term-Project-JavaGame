package ca.bcit.comp2522.termproject.javagame;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

/**
 * Abstract representation of Slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 1.0
 */
public abstract class Slime extends Circle implements Runnable {

    /**
     * The initial radius of a Slime object.
     */
    public static final int INITIAL_RADIUS = 10;
    /**
     * The initial size of a Slime object.
     */
    public static final int INITIAL_SIZE = 1;
    /**
     * The maximum velocity of the slime.
     */
    public static final int MAX_VELOCITY = 6;
    private static final int MAX_X = 425; // horizontal edge of enclosing Panel
    private static final int MAX_Y = 475; // vertical edge of enclosing Panel
    private static final int MIN_X = 25; // horizontal edge of enclosing Panel
    private static final int MIN_Y = 75; // vertical edge of enclosing Panel
    private static final Random GENERATOR = new Random();
    private static int totalNumberOfSlime;
    private static final int MAX_SLIME_SIZE_FOR_SPLIT = 10;
    private static final int MAX_ROTATION_ANGLE = 180;
    private static final int ROTATION_DURATION_MILLIS = 200;
    private static final int THREAD_SLEEP_MILLIS = 20;
    /**
     * The ImageView for slime's image.
     */
    protected ImageView imageView;
    /**
     * The instance of slime-specific behaviors.
     */
    protected SlimeBehavioralImplementation slimeBehavior;
    private int size = INITIAL_SIZE;
    private String slimeImage;
    private double xVelocity;
    private double yVelocity;
    private boolean isTest;
    private int price;
    private final String slimeNAME;
    private final int slimeId;

    private boolean isAlive;
    private Thread thread;
    private final PetriDish petriDish;
    private boolean running = true;


    /**
     * Constructs a Slime object with specified position and petri dish.
     *
     * @param xPosition the initial X position of the slime
     * @param yPosition the initial Y position of the slime
     * @param petriDish the Petri dish of the slime
     */
    public Slime(final double xPosition, final double yPosition, final PetriDish petriDish, final boolean isTest) {
        super(INITIAL_RADIUS, Color.TRANSPARENT);
        totalNumberOfSlime++;
        this.slimeId = totalNumberOfSlime;
        this.slimeNAME = setConstantName();
        this.setSlimeImage(getConstantSlimeImageName());
        this.setCenterX(xPosition);
        this.setCenterY(yPosition);
        this.petriDish = petriDish;
        this.slimeBehavior = new SlimeBehavioralImplementation();
        xVelocity = GENERATOR.nextInt(1, MAX_VELOCITY); // change in x (0 - 4 pixels)
        yVelocity = GENERATOR.nextInt(1, MAX_VELOCITY); // change in y (0 - 4 pixels)
        this.isTest = isTest;
        if (!this.isTest) {
            Image image = new Image(getConstantSlimeImageName());
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            //image size match slime size
            imageView.setFitHeight(getRadius() * 2);
            imageView.setFitWidth(getRadius() * 2);
            //slime's centre same with image centre
            imageView.setX(getCenterX() - getRadius());
            imageView.setY(getCenterY() - getRadius());
        }
    }

    /**
     * Constructs a Slime object with specified position and petri dish.
     *
     * @param xPosition the initial X position of the slime
     * @param yPosition the initial Y position of the slime
     * @param petriDish the Petri dish of the slime
     */
    public Slime(final double xPosition, final double yPosition, final PetriDish petriDish){
        this(xPosition,yPosition,petriDish,false);
    }

    //abstract methods
    protected abstract String setConstantName();

    /**
     * Bounces the Ball perpetually.
     */
    public void run() {
        while (running && !petriDish.getIsStopThread()) {
            synchronized (this) {
                try {
                    wait(THREAD_SLEEP_MILLIS);
                } catch (InterruptedException exception) {
                    System.out.println("Thread interrupted");
                }
            }
            Platform.runLater(this::checkBounds);
        }
    }

    public void checkBounds() {
        if (this.getCenterY() - this.getRadius() <= MIN_Y && this.getYVelocity() < 0) {
            yVelocity = GENERATOR.nextInt(1, MAX_VELOCITY);
            reachEdgeAction();
        } else if (this.getCenterY() + this.getRadius() >= MAX_Y && this.getYVelocity() > 0) {
            yVelocity = GENERATOR.nextInt(1, MAX_VELOCITY) * -1;
            reachEdgeAction();
        }
        if (this.getCenterX() - this.getRadius() <= MIN_X && this.getXVelocity() < 0) {
            xVelocity = GENERATOR.nextInt(1, MAX_VELOCITY);
            reachEdgeAction();
        } else if (this.getCenterX() + this.getRadius() >= MAX_X && this.getXVelocity() > 0) {
            xVelocity = GENERATOR.nextInt(1, MAX_VELOCITY) * -1;
            reachEdgeAction();
        }
        // Update the position of the ball
        this.setCenterX(this.getCenterX() + xVelocity); // determines new x-position
        this.setCenterY(this.getCenterY() + yVelocity); // determines new y-position

        if (imageView != null) {
            imageView.setX(this.getCenterX() - this.getRadius());
            imageView.setY(this.getCenterY() - this.getRadius());
        }
    }

    private void reachEdgeAction() {
        animateRotation(imageView, GENERATOR.nextInt(MAX_ROTATION_ANGLE));
        this.grow(1);
        if (this.size >= MAX_SLIME_SIZE_FOR_SPLIT) {
            slimeBehavior.split(petriDish, this);
        }
        petriDish.checkSlimeCountAndKill();
    }

    /**
     * Starts the thread for the slime.
     */
    public void startThread() {
        Thread bouncer = new Thread(this);
        bouncer.setDaemon(true);
        bouncer.start();
        this.thread = bouncer;
    }

    /**
     * Animates the rotation of the slime's image view.
     *
     * @param newImageView the image view to animate
     * @param angle        the angle to rotate to
     */
    private void animateRotation(final ImageView newImageView, final double angle) {
        RotateTransition rotateTransition =
                new RotateTransition(Duration.millis(ROTATION_DURATION_MILLIS), newImageView);
        rotateTransition.setToAngle(angle);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }

    /**
     * Adds the slime to the specified pane.
     *
     * @param pane the pane to add the slime to
     */
    public void addToPane(final Pane pane) {
        pane.getChildren().add(this);
        if (imageView != null) {
            pane.getChildren().add(imageView);
        }
    }

    /**
     * Increases the size of the slime based on the growth coefficient.
     *
     * @param growCoefficient the coefficient determining the amount of growth
     */
    public void grow(final int growCoefficient) {
        this.size += growCoefficient;
        this.setRadius(this.getRadius() + 2 * growCoefficient);
        if (!this.isTest) {
            imageView.setFitHeight(getRadius() * 2);
            imageView.setFitWidth(getRadius() * 2);
        }
    }

    /**
     * Shrinks the slime to its initial size.
     */
    public void shrinkSlimeToBaby() {
        this.size = INITIAL_SIZE;
        this.setRadius(INITIAL_RADIUS);
        if (imageView != null) {
            this.imageView.setFitWidth(INITIAL_RADIUS * 2);
            this.imageView.setFitHeight(INITIAL_RADIUS * 2);
        }
    }

    /**
     * Kills the slime by setting its alive status to false and stopping its thread.
     * The slime's image view is also updated to show it as dead.
     */
    public void die() {
        this.setAlive(false);
        if (!this.isTest) {
            this.petriDish.removeThread(this.thread);
            this.setDeadImageView();
            this.stopThread();
        }
    }


    private void setDeadImageView() {
        if (!this.isTest) {
            Image deadImage = new Image("deadSlime.png");
            this.imageView.setImage(deadImage);
        }
    }

    /**
     * Sets the image view of the slime to the dead image.
     */
    public void stopThread() {
        running = false;
    }

    /**
     * Gets the ID of the slime.
     *
     * @return the slime's ID
     */
    public int getSlimeId() {
        return slimeId;
    }

    /**
     * Gets the name of the slime.
     *
     * @return the name of this slime
     */
    public String getName() {
        return this.slimeNAME;
    }

    /**
     * Gets the x velocity of the slime.
     *
     * @return the x velocity of this slime
     */
    public double getXVelocity() {
        return this.xVelocity;
    }

    /**
     * Gets the alive status of the slime.
     *
     * @return boolean value representing this slime's living status
     */

    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Gets the price of the slime.
     *
     * @return the price of the slime
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Gets the thread associated with the slime.
     *
     * @return the thread associated with the slime
     */
    public Thread getThread() {
        return this.thread;
    }
    /**
     * Gets if the Slime is Alive.
     *
     * @return boolean representing if slime alive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Gets the y velocity of the slime.
     *
     * @return the y velocity of the slime
     */
    public double getYVelocity() {
        return this.yVelocity;
    }

    protected abstract String getConstantSlimeImageName();

    /**
     * Gets the image of the slime.
     *
     * @return the image of the slime
     */
    public String getSlimeImage() {
        return this.slimeImage;
    }

    //    setters

    /**
     * Sets the price of the slime.
     *
     * @param newPrice the new price of the slime
     */
    public void setPrice(final int newPrice) {
        this.price = newPrice;
    }

    /**
     * Sets the alive status of the slime.
     *
     * @param newIsAlive the new alive status of the slime
     */
    public void setAlive(final boolean newIsAlive) {
        if (this.isAlive) {
            this.isAlive = newIsAlive;
        }
    }

    /**
     * Sets the image of the slime.
     *
     * @param newImage the new image of the slime
     */
    public void setSlimeImage(final String newImage) {
        if (newImage != null) {
            this.slimeImage = newImage;
        }
    }

}
