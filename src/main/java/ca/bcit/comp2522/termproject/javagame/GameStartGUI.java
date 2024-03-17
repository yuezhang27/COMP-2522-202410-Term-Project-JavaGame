package ca.bcit.comp2522.termproject.javagame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

public class GameStartGUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Image backgroundImg = new Image("slimeBackground.png");
        ImageView imageView = new ImageView(backgroundImg);

        // 创建一个Button
        Button startGameButton = new Button("START GAME!");
        Circle circle = new Circle(50);
        startGameButton.setShape(circle);
        startGameButton.setMinSize(100, 100);
        startGameButton.setMaxSize(100, 100);
        startGameButton.setTranslateY(100);
        startGameButton.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        //connect the 2 stage
        startGameButton.setOnAction(event -> {
            System.out.println("Game Start!");
            primaryStage.close();
            Platform.runLater(() -> {
                try {
                    new BouncingSlimes().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });


        //创建一个StackPane，把Button添加到布局中
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
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
