package ca.bcit.comp2522.termproject.javagame;

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


/**
 * BouncingBalls, an introduction to threading and JavaFX.
 *
 * @author BCIT
 * @author YOUR NAME GOES HERE
 * @version 2022
 */
public class BouncingSlimes extends Application {
    public static final int WINDOW_SIZE_X = 500;
    public static final int WINDOW_SIZE_Y = 500;
    public static final int SELL_WINDOW_SIZE_X = 300;
    public static final int SELL_WINDOW_SIZE_Y = 300;
    public static final int SELL_BTN_ICON_WIDTH = 25;
    public static final int SELL_BTN_ICON_HEIGHT = 25;
    public static final int SELL_BTN_ICON_X_COORDINATE = 25;
    public static final int SELL_BTN_ICON_Y_COORDINATE = 10;
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 200;
    private static final int MOUSE_ENTER_WIDTH_INCREMENT = 10;
    private static final int BUTTON_LAYOUT_X = 370;
    private static final int BUTTON_LAYOUT_Y = 420;
    private static final int INITIAL_LAYOUT_X = 280;
    private static final int INITIAL_LAYOUT_Y = 10;
    private static final int SLIME_CATEGORY_SIZE = 10;
    final double DEFAULT_SLIME_INITIAL_X = 250;
    final double DEFAULT_SLIME_INITIAL_Y = 250;
    private PetriDish petriDish;
    private Player player;
    private Label balanceLabel;
    private ProgressBarManager progressBarManager;


    /**
     * Demonstrates threading in JavaFX.
     *
     * @param primaryStage contains the Scene
     */
    public void start(final Stage primaryStage) {
        initializeGame();
        Scene scene = createScene();
        scene.getStylesheets().add("style.css");

        primaryStage.getIcons().add(new Image("pinkSlime.png"));
        primaryStage.setTitle("Threads and Balls");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
//        add a default slime to start the game
        addDefaultSlime();
    }

    private void initializeGame() {
        petriDish = new PetriDish();
        player = new Player();
        balanceLabel = createBalanceLabel();
        progressBarManager = new ProgressBarManager(petriDish);
    }

    private Scene createScene() {
        StackPane rootPane = new StackPane();
        Pane petriDishCanvas = setupCanvas();
        Pane buttonCanvas = createButtonCanvas();
        rootPane.getChildren().addAll(petriDishCanvas, buttonCanvas);
        return new Scene(rootPane, WINDOW_SIZE_X, WINDOW_SIZE_Y);
    }

    private Pane setupCanvas() {
        Pane canvas = petriDish.getCanvas();
        ImageView imageView = new ImageView(new Image(PetriDish.PETRI_DISH_IMAGE));
        canvas.getChildren().add(imageView);
        ProgressBar progressBar = progressBarManager.getProgressBar();
        canvas.getChildren().addAll(balanceLabel, progressBar);
        return canvas;
    }

    private Pane createButtonCanvas() {
        Pane buttonCanvas = new Pane();
        buttonCanvas.setPrefSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        Button sellButton = createSellBtn();
        sellButton.setOnMouseClicked(mouseEvent -> openSlimeListView());
        ImageView soupButton = createSoupBtn();
        soupButton.setOnMouseClicked(mouseEvent -> reduceBalanceAddSoup());
        buttonCanvas.getChildren().addAll(sellButton, soupButton);
        return buttonCanvas;
    }

    private void openSlimeListView() {
        ListView<Slime> slimeListView = createSlimeListView();
        Stage listStage = new Stage();
        listStage.setTitle("Slime List");
        Image icon = new Image("purpleSlime.png");
        listStage.getIcons().add(icon);

        Scene listScene = new Scene(new StackPane(slimeListView), SELL_WINDOW_SIZE_X, SELL_WINDOW_SIZE_Y);
        listStage.setScene(listScene);
        listStage.show();
    }

    private Button createSellBtn() {
        Button sellButton = new Button("Sell Slimes");
        sellButton.getStyleClass().add("button-sell");
        //Logo of Sell button
        Image sellButtonImage = new Image("SellButton.png");
        ImageView sellImageView = new ImageView(sellButtonImage);
        sellButton.setGraphic(sellImageView);

        sellImageView.setFitHeight(SELL_BTN_ICON_HEIGHT);  //Button height
        sellImageView.setFitWidth(SELL_BTN_ICON_WIDTH);   // Button width
        sellImageView.setPreserveRatio(true);

        //Coordination of sell button
        sellButton.setLayoutX(SELL_BTN_ICON_X_COORDINATE);
        sellButton.setLayoutY(SELL_BTN_ICON_Y_COORDINATE);
        sellButton.setOnMouseClicked(event -> createSlimeListView());
        return sellButton;
    }

    private ImageView createSoupBtn() {
        Image image = new Image("soup_btn.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(BUTTON_WIDTH);
        imageView.setFitHeight(BUTTON_HEIGHT);
        imageView.setPreserveRatio(true);

        imageView.setLayoutX(BUTTON_LAYOUT_X);
        imageView.setLayoutY(BUTTON_LAYOUT_Y);

        imageView.setOnMouseEntered(mouseEvent -> {
            imageView.setFitWidth(BUTTON_WIDTH+MOUSE_ENTER_WIDTH_INCREMENT);
            imageView.setPreserveRatio(true);
        });
        imageView.setOnMouseExited(mouseEvent -> {
            imageView.setFitWidth(BUTTON_WIDTH);
            imageView.setPreserveRatio(true);
        });
        return imageView;
    }

    private void reduceBalanceAddSoup(){
        if (player.reduceBalance(1)) {
            progressBarManager.updateTimeline(0.25, 40);
            balanceLabel.setText("\uD83D\uDCB0Balance $: " + player.getBalance());
        }
    }

    private Label createBalanceLabel() {
        Label balanceLabel = new Label("\uD83D\uDCB0Balance $: " + player.getBalance());
        balanceLabel.setLayoutX(INITIAL_LAYOUT_X);
        balanceLabel.setLayoutY(INITIAL_LAYOUT_Y);
        balanceLabel.getStyleClass().add("balance-label");
        balanceLabel.setFont(Font.font("Segoe UI Emoji"));
        return balanceLabel;
    }

    private ListView<Slime> createSlimeListView() {
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
                    slimeCategory.setFitWidth(SLIME_CATEGORY_SIZE);
                    slimeCategory.setFitHeight(SLIME_CATEGORY_SIZE);

                    Button button = new Button("Sell");
                    button.setOnAction(e -> {
                        addEventToSingleSellButton(slime, slimeListView);
                    });
                    hbox.getChildren().addAll(button, slimeCategory);
                    setGraphic(hbox);
                }
            }
        });
        return slimeListView;
    }

    private void addEventToSingleSellButton(Slime slime, ListView<Slime> listView) {
            petriDish.removeSlime(slime);
            petriDish.getCanvas().getChildren().remove(slime.imageView);
            player.increaseBalance(slime.getPrice());
            System.out.println(player.getBalance());
            balanceLabel.setText("\uD83D\uDCB0Balance $: " + player.getBalance());
            listView.setItems(FXCollections.observableArrayList(petriDish.getSlimesList()));
    }

    private void addDefaultSlime() {
        Slime defaultSlime = new YellowSlime(DEFAULT_SLIME_INITIAL_X, DEFAULT_SLIME_INITIAL_Y, petriDish);
        defaultSlime.addToPane(petriDish.getCanvas());
        defaultSlime.startThread();
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
