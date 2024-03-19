package ca.bcit.comp2522.termproject.javagame;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;




/**
 * BouncingBalls, an introduction to threading and JavaFX.
 *
 * @author BCIT
 * @author YOUR NAME GOES HERE
 * @version 2022
 */
public class BouncingSlimes extends Application {


    private static double balance;
    public static double getBalance() {
        return balance;
    }

//    public static void setBalance(double balance) {
//        BouncingSlimes.balance = balance;
//    }



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
        scene.getStylesheets().add("style.css");
        Button sellButton = new Button("Sell Slimes");
        sellButton.getStyleClass().add("button-sell");
        //Logo of Sell button
        Image sellButtonImage = new Image("SellButton.png");
//        Image sellButtonImage = new Image("purpleSlime.png");
        ImageView sellImageView = new ImageView(sellButtonImage);

        sellImageView.setFitHeight(25);  //Button height
        sellImageView.setFitWidth(25);   // Buttin width
        sellImageView.setPreserveRatio(true);

        sellButton.setGraphic(sellImageView);
        //Coordination of sell button
        sellButton.setLayoutX(25);
        sellButton.setLayoutY(10);
        //Sell button onclick event handler
        sellButton.setOnAction(event -> {
            System.out.println("Sell button clicked");
            ListView<Slime> slimeListView = new ListView<>(FXCollections.observableArrayList(petriDish.getSlimesList()));
            slimeListView.setCellFactory(lv -> new ListCell<Slime>() {
                @Override
                protected void updateItem(Slime slime, boolean empty) {
                    super.updateItem(slime, empty);
                    if (empty || slime == null) {
                        setText(null);
                    } else {
                        setText("Slime " + slime.getSlimeId() + " - $" + slime.getPrice());
                    }
                }
            });
            // Coordination X, Y of the Listview (Slime list)
            slimeListView.setLayoutX(50);
            slimeListView.setLayoutY(100);
            slimeListView.setPrefSize(200, 300);

            // AVOID REPETITION
            canvas.getChildren().removeIf(node -> node instanceof ListView);
            canvas.getChildren().add(slimeListView);
        });

        canvas.getChildren().add(sellButton);
        //HARD CODE BALANCE FOR 500 FOR NOW!!
        Label balanceLabel = new Label("Balance $: "+getBalance());
        balanceLabel.setLayoutX(300);
        balanceLabel.setLayoutY(10);
        balanceLabel.getStyleClass().add("balance-label");
        canvas.getChildren().add(balanceLabel);
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
