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

public abstract class Slime extends Circle implements Runnable {

    private static final int MAX_X = 425; // horizontal edge of enclosing Panel
    private static final int MAX_Y = 475; // vertical edge of enclosing Panel
    private static final int MIN_X = 25; // horizontal edge of enclosing Panel
    private static final int MIN_Y = 75; // vertical edge of enclosing Panel
    public static final int INITIAL_RADIUS = 10;
//    public static final int INITIAL_RADIUS = 10;
    public static final int INITIAL_SIZE = 1;
    private int size = INITIAL_SIZE;
    private String slimeImage;
    private double xVelocity;
    private double yVelocity;
    private int price;
    private final String NAME;
    public static int totalNumberOfSlime;
    private final int slimeId;
    private boolean isAlive;
    private Thread thread;
    private final PetriDish petriDish;
    private boolean running = true;
    private static final Random GENERATOR = new Random();
    protected ImageView imageView;
    protected SlimeBehavioralImplementation slimeBehavior;


    public Slime(final double xPosition, final double yPosition, PetriDish petriDish) {
        super(INITIAL_RADIUS, Color.TRANSPARENT);
        totalNumberOfSlime++;
        this.slimeId = totalNumberOfSlime;
        this.NAME = setConstantName();
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
        imageView.setFitHeight(getRadius() * 2); // 根据Slime大小调整图片大小
        imageView.setFitWidth(getRadius() * 2);

//         确保图片与Slime的中心对齐
        imageView.setX(getCenterX() - getRadius());
        imageView.setY(getCenterY() - getRadius());
    }

    public int getSize() {
        return this.size;
    }
    public int getSlimeId() {
        return slimeId;
    }
    public String getName() {return this.NAME;}

// getters

    public double getXVelocity() {
        return this.xVelocity;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

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
