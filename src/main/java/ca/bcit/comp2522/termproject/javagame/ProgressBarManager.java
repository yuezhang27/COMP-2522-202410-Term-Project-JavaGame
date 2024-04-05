package ca.bcit.comp2522.termproject.javagame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class ProgressBarManager {

    private final ProgressBar progressBar;
    private final Timeline timeline;
    private final PetriDish petriDish;
    public ProgressBarManager(PetriDish petriDish){
        this.progressBar = createVerticalProgressBar();
        this.timeline = createNewTimeline();
        this.petriDish = petriDish;
        addActionToProgressBar();
    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }
    private ProgressBar createVerticalProgressBar() {
        ProgressBar progressBar = new ProgressBar();
        progressBar.getStyleClass().add("vertical-progress-bar");
        progressBar.setPrefSize(400, 25);
        progressBar.setLayoutX(280);
        progressBar.setLayoutY(200);
        return progressBar;
    }
    private Timeline createNewTimeline() {
        return new Timeline(
                new KeyFrame(Duration.seconds(20), new KeyValue(progressBar.progressProperty(), 0)),
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
                gameEnd(petriDish);
            }
        }));
    }


    public void updateTimeline(double increasedPercentage, double totalTime) {
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

    void gameEnd(PetriDish petriDish) {
        petriDish.setStopThread(true);

    }


}
