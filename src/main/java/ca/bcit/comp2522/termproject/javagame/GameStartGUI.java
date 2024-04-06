package ca.bcit.comp2522.termproject.javagame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Platform;

/**
 * GameStartGUI class represents the GUI for starting the game.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class GameStartGUI extends Application {
    private static final int BUTTON_SIZE = 100;
    private static final int BUTTON_TRANSLATE_Y = 100;
    private static final int CIRCLE_RADIUS = 50;
    private static final int FONT_SIZE = 20;

    /**
     * Starts the application by creating the primary stage with a background image and a start game button.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    @Override
    public void start(final Stage primaryStage) {

        ImageView background = createBackground();
        Button startGameButton = createStartGameButton(primaryStage);
        StackPane root = new StackPane();
        root.getChildren().addAll(background, startGameButton);

        Scene scene = new Scene(root, BouncingSlimes.WINDOW_SIZE_X, BouncingSlimes.WINDOW_SIZE_Y);
        setupPrimaryStage(primaryStage, scene);
        primaryStage.show();
    }

    private ImageView createBackground() {
        Image backgroundImg = new Image("slimeBackground.png");
        return new ImageView(backgroundImg);
    }

    private Button createStartGameButton(final Stage primaryStage) {
        Button startGameButton = new Button("START\nGAME!");
        Circle circle = new Circle(CIRCLE_RADIUS);
        startGameButton.setShape(circle);
        startGameButton.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        startGameButton.setTranslateY(BUTTON_TRANSLATE_Y);
        startGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, FONT_SIZE));
        startGameButton.setOnAction(actionEvent -> startGame(primaryStage));
        return startGameButton;
    }

    private void setupPrimaryStage(final Stage primaryStage, final Scene scene) {
        Image icon = new Image("yellowSlime.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Slime Container");
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
    }

    private void startGame(final Stage primaryStage) {
        primaryStage.close();
        Platform.runLater(() -> new BouncingSlimes().start(new Stage()));
    }

    /**
     * The entry point for launching the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
