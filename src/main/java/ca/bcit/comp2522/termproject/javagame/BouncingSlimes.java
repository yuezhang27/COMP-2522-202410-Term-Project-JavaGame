package ca.bcit.comp2522.termproject.javagame;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * BouncingBalls, an introduction to threading and JavaFX.
 *
 * @author BCIT
 * @author YOUR NAME GOES HERE
 * @version 2022
 */
public class BouncingSlimes extends Application {
    /**
     * The width of the main window.
     */
    public static final int WINDOW_SIZE_X = 500;

    /**
     * The height of the main window.
     */
    public static final int WINDOW_SIZE_Y = 500;

    /**
     * The width of the sell window.
     */
    public static final int SELL_WINDOW_SIZE_X = 300;

    /**
     * The height of the sell window.
     */
    public static final int SELL_WINDOW_SIZE_Y = 300;

    /**
     * The width of the sell button icon.
     */
    public static final int SELL_BTN_ICON_WIDTH = 25;

    /**
     * The height of the sell button icon.
     */
    public static final int SELL_BTN_ICON_HEIGHT = 25;

    /**
     * The x-coordinate of the sell button icon.
     */
    public static final int SELL_BTN_ICON_X_COORDINATE = 25;

    /**
     * The y-coordinate of the sell button icon.
     */
    public static final int SELL_BTN_ICON_Y_COORDINATE = 10;
    /**
     * The default initial x-coordinate for a slime.
     */
    public static final double DEFAULT_SLIME_INITIAL_X = 250;

    /**
     * The default initial y-coordinate for a slime.
     */
    public static final double DEFAULT_SLIME_INITIAL_Y = 250;
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 200;
    private static final int MOUSE_ENTER_WIDTH_INCREMENT = 10;
    private static final int BUTTON_LAYOUT_X = 370;
    private static final int BUTTON_LAYOUT_Y = 420;
    private static final int INITIAL_LAYOUT_X = 280;
    private static final int INITIAL_LAYOUT_Y = 10;
    private static final int SLIME_CATEGORY_SIZE = 10;
    private static final int LISTVIEW_ITEM_NUMBER = 10;
    private static final double PROGRESS_BAR_DURATION = 0.25;
    private static final int PROGRESS_BAR_SIZE = 40;
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

    }

    private Scene createScene() {
        StackPane rootPane = new StackPane();
        Pane buttonCanvas = createButtonCanvas();
        Pane petriDishCanvas = setupCanvas();
        rootPane.getChildren().addAll(petriDishCanvas, buttonCanvas);
        return new Scene(rootPane, WINDOW_SIZE_X, WINDOW_SIZE_Y);
    }
    private Pane createButtonCanvas() {
        Pane buttonCanvas = new Pane();
        buttonCanvas.setPrefSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
        Button sellButton = createSellBtn();
        sellButton.setOnMouseClicked(mouseEvent -> openSlimeListView());
        ImageView soupButton = createSoupBtn();
        soupButton.setOnMouseClicked(mouseEvent -> reduceBalanceAddSoup());
        Button clearButton = createClearButton();
        clearButton.setOnMouseClicked(mouseEvent -> clearDeadSlimes());
        buttonCanvas.getChildren().addAll(sellButton, soupButton, clearButton);
        progressBarManager = new ProgressBarManager(petriDish, buttonCanvas);
        return buttonCanvas;
    }
    private Pane setupCanvas() {
        Pane canvas = petriDish.getCanvas();
        ImageView imageView = new ImageView(new Image(PetriDish.PETRI_DISH_IMAGE));
        canvas.getChildren().add(imageView);
        ProgressBar progressBar = progressBarManager.getProgressBar();
        canvas.getChildren().addAll(balanceLabel, progressBar);
        return canvas;
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
        return sellButton;
    }

    private Button createClearButton() {
        Button clearButton = new Button("Clear Dead Slimes");
        clearButton.getStyleClass().add("button-clear");
        clearButton.setLayoutX(150);
        clearButton.setLayoutY(14);
        clearButton.setTextFill(Color.BLACK);
        petriDish.getCanvas().requestLayout();
        petriDish.getCanvas().layout();
        return clearButton;

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
            imageView.setFitWidth(BUTTON_WIDTH + MOUSE_ENTER_WIDTH_INCREMENT);
            imageView.setPreserveRatio(true);
        });
        imageView.setOnMouseExited(mouseEvent -> {
            imageView.setFitWidth(BUTTON_WIDTH);
            imageView.setPreserveRatio(true);
        });
        return imageView;
    }

    private void reduceBalanceAddSoup() {
        if (player.reduceBalance(1)) {
            progressBarManager.updateTimeline(PROGRESS_BAR_DURATION, PROGRESS_BAR_SIZE);
            balanceLabel.setText("\uD83D\uDCB0Balance $: " + player.getBalance());
        }
    }

    private void clearDeadSlimes() {
        ArrayList<Slime> slimeDeadArrayList = petriDish.getDeadSlimeList();
        Pane canvas = petriDish.getCanvas();
        Iterator<Slime> iterator = slimeDeadArrayList.iterator();
        while (iterator.hasNext()) {
            Slime slime = iterator.next();
            canvas.getChildren().remove(slime.getImageView());
            iterator.remove();
        }
    }

    private Label createBalanceLabel() {
        Label newBalanceLabel = new Label("\uD83D\uDCB0Balance $: " + player.getBalance());
        newBalanceLabel.setLayoutX(INITIAL_LAYOUT_X);
        newBalanceLabel.setLayoutY(INITIAL_LAYOUT_Y);
        newBalanceLabel.getStyleClass().add("balance-label");
        newBalanceLabel.setFont(Font.font("Segoe UI Emoji"));
        return newBalanceLabel;
    }

    private ListView<Slime> createSlimeListView() {
        ListView<Slime> slimeListView = new ListView<>(FXCollections.observableArrayList(petriDish.getSlimesList()));
        slimeListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(final Slime slime, final boolean empty) {
                super.updateItem(slime, empty);
                if (empty || slime == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(slime.getName() + slime.getSlimeId() + " - $" + slime.getPrice());
                    HBox hbox = new HBox(LISTVIEW_ITEM_NUMBER);
                    hbox.setAlignment(Pos.CENTER_RIGHT);

                    ImageView slimeCategory = new ImageView(slime.getSlimeImage());
                    slimeCategory.setFitWidth(SLIME_CATEGORY_SIZE);
                    slimeCategory.setFitHeight(SLIME_CATEGORY_SIZE);

                    Button button = new Button("Sell");
                    button.setOnAction(e ->
                            addEventToSingleSellButton(slime, slimeListView)
                    );
                    hbox.getChildren().addAll(button, slimeCategory);
                    setGraphic(hbox);
                }
            }
        });
        return slimeListView;
    }

    private void addEventToSingleSellButton(final Slime slime, final ListView<Slime> listView) {
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
        petriDish.setDefaultSlime(defaultSlime);
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
