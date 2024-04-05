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
 *  @author Caroline Su Github: Juntingg
 * @author Kim Zhang Github:yuezhang27
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
    private static final int MAX_X = 425; // horizontal edge of enclosing Panel
    private static final int MAX_Y = 475; // vertical edge of enclosing Panel
    private static final int MIN_X = 25; // horizontal edge of enclosing Panel
    private static final int MIN_Y = 75; // vertical edge of enclosing Panel
    private static final Random GENERATOR = new Random();
    private static int totalNumberOfSlime;
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
     * @param xPosition  the initial X position of the slime
     * @param yPosition  the initial Y position of the slime
     * @param petriDish  the Petri dish of the slime
     */
    public Slime(final double xPosition, final double yPosition, final PetriDish petriDish) {
        super(INITIAL_RADIUS, Color.TRANSPARENT);
        totalNumberOfSlime++;
        this.slimeId = totalNumberOfSlime;
        this.slimeNAME = setConstantName();
        this.setSlimeImage(getConstantSlimeImageName());
        this.setCenterX(xPosition);
        this.setCenterY(yPosition);
        this.petriDish = petriDish;
        this.slimeBehavior = new SlimeBehavioralImplementation();
        xVelocity = GENERATOR.nextInt(1, 6); // change in x (0 - 4 pixels)
        yVelocity = GENERATOR.nextInt(1, 6); // change in y (0 - 4 pixels)
        Image image = new Image(getConstantSlimeImageName()); // 替换为实际图片路径
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        //image size match slime size
        imageView.setFitHeight(getRadius() * 2);
        imageView.setFitWidth(getRadius() * 2);
        //slime's centre same with image centre
        imageView.setX(getCenterX() - getRadius());
        imageView.setY(getCenterY() - getRadius());
    }
    /**
     * Gets the size of the slime.
     *
     * @return the size of this slime
     */
    public int getSize() {
        return this.size;
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
    public boolean getIsAlive() {
        return this.isAlive;
    }
    /**
     * Gets the image of the slime.
     *
     * @return the image of this slime
     */
    public ImageView getImageView() {
        return this.imageView;
    }

    public int getPrice() {
        return this.price;
    }

    public Thread getThread() {
        return this.thread;
    }

    public double getYVelocity() {
        return this.yVelocity;
    }

    //    setters

    public void setXVelocity(double newXVelocity) {
        this.xVelocity = newXVelocity;
    }

    public void setYVelocity(double newYVelocity) {
        this.yVelocity = newYVelocity;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public void setAlive(boolean newIsAlive) {
        if (this.isAlive) {
            this.isAlive = newIsAlive;
        }
    }

    public void setSize(int newSize) {
        if (newSize >= 0 && newSize <= 30) {
            this.size = newSize;
        }
    }

    //abstract methods
    protected abstract String setConstantName();
    protected abstract SlimeType setConstantSlimeType(SlimeType slimeType);

    protected abstract String getConstantSlimeImageName();
    public String getSlimeImage(){
        return this.slimeImage;
    }
    public void setSlimeImage(String newImage){
        if (newImage != null) {
            this.slimeImage = newImage;
        }
    }

    /**
     * Bounces the Ball perpetually.
     */
    public void run() {
        while (running && !petriDish.getIsStopThread()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            Platform.runLater(this::checkBounds);
        }
    }
    private void checkBounds(){
        if (this.getCenterY() - this.getRadius() <= MIN_Y && this.getYVelocity() < 0) {
            yVelocity = GENERATOR.nextInt(1, 6);
            reachEdgeAction();
        } else if (this.getCenterY() + this.getRadius() >= MAX_Y && this.getYVelocity() > 0) {
            yVelocity = GENERATOR.nextInt(1, 6) * -1;
            reachEdgeAction();
        }
        if (this.getCenterX() - this.getRadius() <= MIN_X && this.getXVelocity() < 0) {
            xVelocity = GENERATOR.nextInt(1, 6);
            reachEdgeAction();
        } else if (this.getCenterX() + this.getRadius() >= MAX_X && this.getXVelocity() > 0) {
            xVelocity = GENERATOR.nextInt(1, 6) * -1;
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

    protected void reachEdgeAction(){
        animateRotation(imageView, GENERATOR.nextInt(180));
        this.grow(1);
        if (this.size >= 10) {
            slimeBehavior.split(petriDish, this);
        }
        petriDish.checkSlimeCountAndKill();
    }

    public void startThread() {
//        slime.addToPane(petriDish.getCanvas());
        Thread bouncer = new Thread(this);
        bouncer.setDaemon(true);
        bouncer.start();
        this.thread = bouncer;
    }

    protected void animateRotation(ImageView imageView, double angle) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(200), imageView);
        rotateTransition.setToAngle(angle);
        rotateTransition.setCycleCount(1);
        rotateTransition.play();
    }

    public void addToPane(Pane pane) {
        pane.getChildren().add(this);
        if (imageView != null) {
            pane.getChildren().add(imageView);
        }
    }

    public void grow(int growCoefficient) {
        this.size += growCoefficient;
        this.setRadius(this.getRadius() + 2 * growCoefficient);
        imageView.setFitHeight(getRadius() * 2);
        imageView.setFitWidth(getRadius() * 2);
    }

    public void shrinkSlimeToBaby() {
        this.size = INITIAL_SIZE;
        this.setRadius(INITIAL_RADIUS);
        this.imageView.setFitWidth(INITIAL_RADIUS * 2);
        this.imageView.setFitHeight(INITIAL_RADIUS * 2);
    }

    public void die() {
        this.setAlive(false);
        this.petriDish.removeThread(this.thread);
        this.setDeadImageView();
        this.stopThread();
    }

    private void setDeadImageView() {
        Image deadImage = new Image("deadSlime.png");
        this.imageView.setImage(deadImage);
    }

    public void stopThread() {
        running = false;
    }

}
