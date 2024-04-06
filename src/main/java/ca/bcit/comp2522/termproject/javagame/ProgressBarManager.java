package ca.bcit.comp2522.termproject.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.ArrayList;


/**
 * ProgressBarManager class manages the progress bar for the PetriDish.
 * It provides methods to create, access, and update the progress bar.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public class ProgressBarManager {
    private static final int PROGRESS_BAR_WIDTH = 400;
    private static final int PROGRESS_BAR_HEIGHT = 25;
    private static final int PROGRESS_BAR_LAYOUT_X = 280;
    private static final int PROGRESS_BAR_LAYOUT_Y = 200;
    private static final int TOTAL_DURATION = 20;
    private static final int GAME_OVER_LAYOUT_Y = 200;
    private static final double DARK_OVERLAY_OPACITY = 0.5;

    private final ProgressBar progressBar;
    private final Timeline timeline;
    private final PetriDish petriDish;
    private final Pane buttonCanvas;

    /**
     * Constructs a ProgressBarManager with the specified PetriDish and button canvas.
     *
     * @param petriDish    The PetriDish object to associate with this manager.
     * @param buttonCanvas The pane where the progress bar will be displayed.
     */
    public ProgressBarManager(final PetriDish petriDish, final Pane buttonCanvas) {
        this.progressBar = createVerticalProgressBar();
        this.timeline = createNewTimeline();
        this.petriDish = petriDish;
        this.buttonCanvas = buttonCanvas;
        addActionToProgressBar();
    }

    /**
     * Retrieves the progress bar managed by this manager.
     *
     * @return The ProgressBar object managed by this manager.
     */
    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    private ProgressBar createVerticalProgressBar() {
        ProgressBar newProgressBar = new ProgressBar();
        newProgressBar.getStyleClass().add("vertical-progress-bar");
        newProgressBar.setPrefSize(PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT);
        newProgressBar.setLayoutX(PROGRESS_BAR_LAYOUT_X);
        newProgressBar.setLayoutY(PROGRESS_BAR_LAYOUT_Y);
        return newProgressBar;
    }

    private Timeline createNewTimeline() {
        return new Timeline(
                new KeyFrame(Duration.seconds(TOTAL_DURATION), new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(0), e -> {
//                    start from 1
                }, new KeyValue(progressBar.progressProperty(), 1))
        );

    }

    private void addActionToProgressBar() {
        timeline.setCycleCount(1);
        timeline.play();
        progressBar.progressProperty().addListener(((observableValue, number, newValue) -> {
            if (newValue.doubleValue() == 0) {
                timeline.stop();
                gameEnd();
            }
        }));
    }

    /**
     * Updates the timeline of the progress bar based on the given percentage and total time.
     *
     * @param percentage The percentage of progress to update.
     * @param totalTime  The total time of the progress.
     */
    public void updateTimeline(final double percentage, final double totalTime) {
        double increasedPercentage = percentage;
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
                new KeyFrame(Duration.seconds(0), e -> {
                }, new KeyValue(progressBar.progressProperty(), newProgressBarPercentage))
        );
        timeline.play();
    }

    /**
     * Ends the game by stopping all slimes, displaying a game over label, and adding a dark overlay.
     */
    void gameEnd() {
        ArrayList<Slime> slimeArrayList = petriDish.getSlimesList();
        for (Slime slime : slimeArrayList) {
            slime.die();
        }
        Slime defaultSlime = petriDish.getDefaultSlime();
        defaultSlime.die();
        System.out.println(petriDish.getThreadList());

        petriDish.setStopThread(true);
        Label gameOverLabel = new Label("Your Slimes All Die!");
        gameOverLabel.getStyleClass().add("gameOver-label");
        gameOverLabel.setPrefWidth(BouncingSlimes.WINDOW_SIZE_X);
        gameOverLabel.setTextAlignment(TextAlignment.CENTER);
        gameOverLabel.setLayoutY(GAME_OVER_LAYOUT_Y);

        Rectangle darkOverlay = new Rectangle(0, 0, buttonCanvas.getWidth(), buttonCanvas.getHeight());
        darkOverlay.setFill(Color.rgb(0, 0, 0, DARK_OVERLAY_OPACITY));
        buttonCanvas.getChildren().addAll(darkOverlay, gameOverLabel);
    }
}
