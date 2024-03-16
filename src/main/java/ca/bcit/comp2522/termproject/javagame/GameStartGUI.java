package ca.bcit.comp2522.termproject.javagame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
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

        // 为按钮设置点击事件处理器
        startGameButton.setOnAction(event -> {
            // Caroline: WRITE GAME START AND INITIALIZE CODE HERE!!!!!!
            System.out.println("游戏开始！");
        });

        //创建一个StackPane，把Button添加到布局中
        StackPane root = new StackPane();
        root.getChildren().add(startGameButton);

        // 创建scene，设置scene大小，把StackPane设置到场景中
        Scene scene = new Scene(root, 300, 250);

        // 设置Stage的标题和scene，然后show
        primaryStage.setTitle("Game start GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
