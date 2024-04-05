package ca.bcit.comp2522.termproject.javagame;


import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;

import java.util.ArrayList;

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
     *
     * @param primaryStage contains the Scene
     */
    public void start(final Stage primaryStage) {
//        set up canvas
        PetriDish petriDish = new PetriDish();
        Pane petriDishCanvas = setupCanvas(petriDish);
        StackPane rootPane = new StackPane();
        Scene scene = new Scene(rootPane, 500, 500);
        scene.getStylesheets().add("style.css");

        Button sellButton = createSellBtn();

        //HARD CODE BALANCE FOR 500 FOR NOW!!
        Player player = new Player();
        Label balanceLabel = createBalanceLabel(player);
        petriDishCanvas.getChildren().add(balanceLabel);

//create a second pane for buttons
        Pane buttonCanvas = new Pane();
        buttonCanvas.setPrefSize(500, 500);

        //Sell button onclick event handler
        sellButton.setOnMouseClicked(event -> {
            ListView<Slime> slimeListView = createSlimeListView(petriDish, player);
            Stage listStage = new Stage();
            listStage.setTitle("Slime List");
            Image icon = new Image("pinkSlime.png");
            listStage.getIcons().add(icon);

            Scene listScene = new Scene(new StackPane(slimeListView), 300, 300);
            listStage.setScene(listScene);
            listStage.show();


//            buttonCanvas.getChildren().addFirst(slimeListView);
//
//            // AVOID REPETITION
//            buttonCanvas.getChildren().removeIf(node -> node instanceof ListView);
//            buttonCanvas.getChildren().add(slimeListView);
        });

        buttonCanvas.getChildren().add(sellButton);


        ProgressBar progressBar = createVerticalProgressBar();


        Timeline timeline = createNewTimeline(progressBar);
        addActionToProgressBar(timeline, progressBar, petriDish);
        petriDishCanvas.getChildren().add(progressBar);

        ImageView soupBtn = createSoupBtn(player, balanceLabel, progressBar, timeline);
        buttonCanvas.getChildren().add(soupBtn);

        rootPane.getChildren().add(petriDishCanvas);
        rootPane.getChildren().add(buttonCanvas);



        Image icon = new Image("pinkSlime.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Threads and Balls");
        primaryStage.setScene(scene);
        primaryStage.show();

//        add a default slime to start the game
        addDefaultSlime(petriDishCanvas, petriDish);

    }



    private Pane setupCanvas(PetriDish petriDish) {
        Image backgroundImg = new Image("regularPetriDish.png");
        ImageView imageView = new ImageView(backgroundImg);
        Pane canvas = petriDish.getCanvas();
        canvas.getChildren().add(imageView);
        return canvas;
    }

    private Button createSellBtn() {
        Button sellButton = new Button("Sell Slimes");
        sellButton.getStyleClass().add("button-sell");
        //Logo of Sell button
        Image sellButtonImage = new Image("SellButton.png");
        ImageView sellImageView = new ImageView(sellButtonImage);
        sellButton.setGraphic(sellImageView);

        sellImageView.setFitHeight(25);  //Button height
        sellImageView.setFitWidth(25);   // Buttin width
        sellImageView.setPreserveRatio(true);

        //Coordination of sell button
        sellButton.setLayoutX(25);
        sellButton.setLayoutY(10);
        return sellButton;
    }

    private ImageView createSoupBtn(Player player, Label balanceLabel, ProgressBar progressBar, Timeline timeline) {
        Image image = new Image("soup_btn.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(200);
        imageView.setLayoutX(370);
        imageView.setLayoutY(420);

        imageView.setOnMouseClicked(mouseEvent -> {
            if (player.reduceBalance(1)) {
                updateTimeline(timeline, progressBar, 0.25, 40);
                balanceLabel.setText("\uD83D\uDCB0Balance $: " + player.getBalance());

            }

        });
        imageView.setOnMouseEntered(mouseEvent -> {
            imageView.setFitWidth(130);
            imageView.setPreserveRatio(true);

        });
        imageView.setOnMouseExited(mouseEvent -> {
            imageView.setFitWidth(120);
            imageView.setPreserveRatio(true);
        });

        return imageView;
    }

    private Label createBalanceLabel(Player player) {
        Label balanceLabel = new Label("\uD83D\uDCB0Balance $: " + player.getBalance());
        balanceLabel.setLayoutX(280);
        balanceLabel.setLayoutY(10);
        balanceLabel.getStyleClass().add("balance-label");
        balanceLabel.setFont(Font.font("Segoe UI Emoji"));
        return balanceLabel;
    }

    private ListView<Slime> createSlimeListView(PetriDish petriDish, Player player) {
        ListView<Slime> slimeListView = new ListView<>(FXCollections.observableArrayList(petriDish.getSlimesList()));
        slimeListView.setCellFactory(lv -> new ListCell<Slime>() {
            @Override
            protected void updateItem(Slime slime, boolean empty) {
                super.updateItem(slime, empty);
                if (empty || slime == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(slime.getName() + slime.getSlimeId() + " - $" + slime.getPrice());
                    HBox hbox = new HBox(10);
                    hbox.setAlignment(Pos.CENTER_RIGHT);

                    ImageView slimeCategory = new ImageView(slime.getSlimeImage());
                    slimeCategory.setFitWidth(10);
                    slimeCategory.setFitHeight(10);

                    Button button = new Button("Sell");
                    button.setOnAction(e -> {
                        addEventToSingleSellButton(button, slime, petriDish, player);
                    });
                    hbox.getChildren().addAll(button, slimeCategory);


                    setGraphic(hbox);
                }
            }
        });
        // Coordination X, Y of the Listview (Slime list)
        slimeListView.setLayoutX(50);
        slimeListView.setLayoutY(100);
        slimeListView.setPrefSize(200, 300);
        return slimeListView;
    }

    private void addEventToSingleSellButton(Button btn, Slime slime, PetriDish petriDish, Player player) {
        btn.setOnMouseClicked(mouseEvent -> {
            petriDish.removeSlime(slime);
            petriDish.getCanvas().getChildren().remove(slime.imageView);
            player.increaseBalance(slime.getPrice());


        });
    }
    private void addDefaultSlime(Pane canvas, PetriDish petriDish) {
        Slime defaultSlime = new YellowSlime(250, 250, petriDish);
        defaultSlime.addToPane(canvas);
        defaultSlime.startThread();
    }

    private ProgressBar createVerticalProgressBar() {
        ProgressBar progressBar = new ProgressBar();
        progressBar.getStyleClass().add("vertical-progress-bar");
        progressBar.setPrefSize(400, 25);
        progressBar.setLayoutX(280);
        progressBar.setLayoutY(200);
        return progressBar;
    }

    private Timeline createNewTimeline(ProgressBar progressBar) {
        return new Timeline(
                new KeyFrame(Duration.seconds(20), new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(0), e-> {
//                    start from 1
                }, new KeyValue(progressBar.progressProperty(), 1))
        );

    }

    private void updateTimeline(Timeline timeline, ProgressBar progressBar, double increasedPercentage, double totalTime) {
        double remainingProgressBar = progressBar.getProgress(); //0.8
        if (remainingProgressBar + increasedPercentage > 1) {
            increasedPercentage = 1 - remainingProgressBar;
        }

        double newProgressBarPercentage = increasedPercentage + remainingProgressBar;
        System.out.println("newProgressBarPercentage" + newProgressBarPercentage);

        double currentDuration = totalTime * newProgressBarPercentage;
        System.out.println("currentDuration" + currentDuration);

        timeline.stop();

        timeline.getKeyFrames().setAll(
                new KeyFrame(Duration.seconds(currentDuration),
                        new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(0), e-> {
                }, new KeyValue(progressBar.progressProperty(), newProgressBarPercentage))
                );

        timeline.play();
    }
    private void addActionToProgressBar(Timeline timeline, ProgressBar progressBar, PetriDish petriDish){
        timeline.setCycleCount(1);
        timeline.play();
        progressBar.progressProperty().addListener(((observableValue, number, newValue) -> {
            if (newValue.doubleValue() == 0) {
                timeline.stop();
                gameEnd(petriDish);
            }
        } ));
    }
    void gameEnd(PetriDish petriDish){
        petriDish.setStopThread(true);

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
