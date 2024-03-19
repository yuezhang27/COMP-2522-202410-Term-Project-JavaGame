package ca.bcit.comp2522.termproject.javagame;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        Image backgroundImg = new Image("regularPetriDish.png");
        ImageView imageView = new ImageView(backgroundImg);

        PetriDish petriDish = new PetriDish();
        Pane canvas = petriDish.getCanvas();
        canvas.getChildren().add(imageView);
        Scene scene = new Scene(canvas, 500, 500);
        Button sellButton = new Button("Sell Slimes");

        //Logo of Sell button
        Image sellButtonImage = new Image("path/to/your/image.png");  // 替换为你的图片路径
        ImageView sellImageView = new ImageView(sellButtonImage);

        sellImageView.setFitHeight(25);  //Button height
        sellImageView.setFitWidth(25);   // Buttin width
        sellImageView.setPreserveRatio(true);

        sellButton.setGraphic(sellImageView);
        //Coordination of sell button
        sellButton.setLayoutX(50);
        sellButton.setLayoutY(50);
        //Sell button onclick event handler
        sellButton.setOnAction(event -> {
            System.out.println("Sell button clicked");
        });

        canvas.getChildren().add(sellButton);

        Slime defaultSlime = new YellowSlime(250, 250, petriDish);
        defaultSlime.addToPane(canvas);
        defaultSlime.startThread();
        Image icon = new Image("pinkSlime.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Threads and Balls");
        primaryStage.setScene(scene);
        primaryStage.show();

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
