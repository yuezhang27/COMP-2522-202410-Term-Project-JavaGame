package ca.bcit.comp2522.termproject.javagame;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public abstract class Slime extends Circle implements Runnable{
    private int size;
    private double xCoordinator;
    private double yCoordinator;
    private double xVelocity;
    private double yVelocity;
    private int price;
    private boolean isAlive;
    private static final Random GENERATOR = new Random();

    private static final int MAX_X = 500; // horizontal edge of enclosing Panel
    private static final int MAX_Y = 500; // vertical edge of enclosing Panel

//    private int dx; // change in horizontal position of ball
//    private int dy; // change in vertical position of ball
    protected ImageView imageView;
    public Slime(final int xPosition, final int yPosition) {
        super(10,Color.TRANSPARENT);
//        this.setFill(Color.TRANSPARENT);
        this.setCenterX(xPosition);
        this.setCenterY(yPosition);
        xVelocity = GENERATOR.nextInt(1,6); // change in x (0 - 4 pixels)
        yVelocity = GENERATOR.nextInt(1,6); // change in y (0 - 4 pixels)
    }
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
    /**
     * Bounces the Ball perpetually.
     */
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(20); // sleep for 20 milliseconds
//            } catch (InterruptedException exception) {
//                exception.printStackTrace();
//            }
//
//            /*
//               Long-running operations must not be run on the JavaFX application
//               thread, since this prevents JavaFX from updating the UI, resulting
//               in a frozen UI.
//
//               Note however that any change to a Node that is part of a "live" scene
//               graph must happen on the JavaFX application thread.
//
//               Platform.runLater can be used to execute those updates on the
//               JavaFX application thread.
//             */
//            Platform.runLater(() -> {
//                // if bounce off top or bottom of Panel
//                if (this.getCenterY() <= 0 || this.getCenterY() >= MAX_Y) {
//                    yVelocity *= -1; // reverses velocity in y direction
//                }
//                // if bounce off left or right of Panel
//                if (this.getCenterX() <= 0 || this.getCenterX() >= MAX_X) {
//                    xVelocity *= -1; // reverses velocity in x direction
//                }
//                this.setCenterX(this.getCenterX() + xVelocity); // determines new x-position
//                this.setCenterY(this.getCenterY() + yVelocity); // determines new y-position
//            });
//        }
//    }
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            Platform.runLater(() -> {
                if (this.getCenterY() <= 0 && this.getYVelocity() < 0) {
                    yVelocity = GENERATOR.nextInt(1, 6);
                    animateRotation(imageView, GENERATOR.nextInt(180));
                } else if (this.getCenterY() >= MAX_Y && this.getYVelocity() > 0) {
                    yVelocity = GENERATOR.nextInt(1, 6) * -1;
                    animateRotation(imageView, GENERATOR.nextInt(180));
                }
                // if bounce off left or right of Panel
                if (this.getCenterX() <= 0 && this.getXVelocity() < 0) {
                    xVelocity = GENERATOR.nextInt(1, 6);
                    animateRotation(imageView, GENERATOR.nextInt(180));
                } else if (this.getCenterX() >= MAX_Y && this.getXVelocity() > 0) {
                    xVelocity = GENERATOR.nextInt(1, 6) * -1;
                    animateRotation(imageView, GENERATOR.nextInt(180));
                }
                // Update the position of the ball
                this.setCenterX(this.getCenterX() + xVelocity); // determines new x-position
                this.setCenterY(this.getCenterY() + yVelocity); // determines new y-position

                if (imageView != null) {
                    imageView.setX(this.getCenterX() - this.getRadius());
                    imageView.setY(this.getCenterY() - this.getRadius());
                }
            });
        }
    }
    private void animateRotation(ImageView imageView, double angle) {
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


}
