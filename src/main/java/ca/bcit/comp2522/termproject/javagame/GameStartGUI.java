package ca.bcit.comp2522.termproject.javagame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameStartGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建一个Button
        Button startGameButton = new Button("START GAME!");
        Circle circle = new Circle(50);
        startGameButton.setShape(circle);
        startGameButton.setMinSize(100, 100);
        startGameButton.setMaxSize(100, 100);
        startGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        // add event listener to the button
        startGameButton.setOnAction(event -> {
            // Caroline: WRITE GAME START AND INITIALIZE CODE HERE!!!!!!
            System.out.println("Game Start!");
        });

        //创建一个StackPane，把Button添加到布局中
        StackPane root = new StackPane();
        root.getChildren().add(startGameButton);

        // 创建scene，设置scene大小，把StackPane设置到场景中
        Scene scene = new Scene(root, 500, 500);

        Image icon = new Image("yellowSlime.png");
        primaryStage.getIcons().add(icon);

        // set primaryStage title and not able to be resized
        primaryStage.setTitle("Slime Container");
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
