package ca.bcit.comp2522.termproject.javagame;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Random;

/**
 * BouncingBalls, an introduction to threading and JavaFX.
 *
 * @author BCIT
 * @author YOUR NAME GOES HERE
 * @version 2022
 */
public class BouncingSlimes extends Application {

    /**
     * Demonstrates threading in JavaFX.
     * @param primaryStage contains the Scene
     */
    public void start(final Stage primaryStage) {
        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 500, 500);
        Slime ball = new YellowSlime(250, 250);
        Slime slime2 = new BlueSlime(150, 150);

        canvas.getChildren().add(ball);

        primaryStage.setTitle("Threads and Balls");
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread bouncer = new Thread(ball);
        bouncer.setDaemon(true);
        bouncer.start();

        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            Slime anotherBall = new YellowSlime(random.nextInt(250), random.nextInt(250));
            Slime anotherBlue = new BlueSlime(random.nextInt(150),random.nextInt(150));
//            canvas.getChildren().add(anotherBall);
            anotherBall.addToPane(canvas);
            anotherBlue.addToPane(canvas);
            Thread bouncingBall = new Thread(anotherBall);
            Thread bouncingBlue = new Thread(anotherBlue);
            bouncingBall.setDaemon(true);
            bouncingBlue.setDaemon(true);
            bouncingBall.start();
            bouncingBlue.start();
        }

    }

    /**
     * Launches the JavaFX application.  We still need a main method in our
     * JavaFX applications.  The main method must do one thing.  Pass
     * the command line arguments (args) to the launch method inherited from
     * the Application class.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
