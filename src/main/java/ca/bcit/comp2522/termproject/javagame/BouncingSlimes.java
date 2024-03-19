package ca.bcit.comp2522.termproject.javagame;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



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
        PetriDish petriDish = new PetriDish();
        Pane canvas = petriDish.getCanvas();
        Scene scene = new Scene(canvas, 500, 500);

        Slime defaultSlime = new YellowSlime(250, 250, petriDish);
        defaultSlime.addToPane(canvas);
        defaultSlime.startThread();

        primaryStage.setTitle("Threads and Balls");
        primaryStage.setScene(scene);
        primaryStage.show();


//        Random random = new Random();
//        for (int i = 0; i < 10; ++i) {
//            Slime anotherBall = new YellowSlime(random.nextInt(500), random.nextInt(500));
//            Slime anotherBlue = new BlueSlime(random.nextInt(500),random.nextInt(500));
////            canvas.getChildren().add(anotherBall);
//            anotherBall.addToPane(canvas);
//            anotherBlue.addToPane(canvas);
//            Thread bouncingBall = new Thread(anotherBall);
//            Thread bouncingBlue = new Thread(anotherBlue);
//            bouncingBall.setDaemon(true);
//            bouncingBlue.setDaemon(true);
//            bouncingBall.start();
//            bouncingBlue.start();
//        }



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
