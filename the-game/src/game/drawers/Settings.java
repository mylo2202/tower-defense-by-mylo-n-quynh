package game.drawers;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Settings {
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private AnchorPane settingsPane;
    private Scene settingsScene;
    private Stage settingsStage;
    private Stage menuStage;

    public Settings() {
        settingsPane = new AnchorPane();

        settingsScene = new Scene(settingsPane, WIDTH, HEIGHT);
        settingsStage = new Stage();
        settingsStage.setResizable(false);
        settingsStage.setScene(settingsScene);
    }

    public void createSettings(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        settingsStage.setTitle("Settings");
        ButtonMusic();
        buttonExit();
        createBackGround();
        settingsStage.show();
    }

    public void buttonExit() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/life.png');";
        MyButton exit = new MyButton("Exit", 45, 100, url);
        exit.setLayoutX(50);
        exit.setLayoutY(300);
        exit.setOnAction(actionEvent -> {
            settingsStage.hide();
            menuStage.show();
        });
        settingsPane.getChildren().add(exit);
    }

    private void createBackGround() {
        Image backgroundImage = new Image("/Image/Background/background1.png", WIDTH, HEIGHT, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        settingsPane.setBackground(new Background(background));
    }

    private void ButtonMusic() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/grey_circle.png');";
        MyButton exit = new MyButton("", 20, 36, url);
        exit.setLayoutX(50);
        exit.setLayoutY(100);
        exit.setOnAction(actionEvent -> {

        });
        settingsPane.getChildren().add(exit);
    }
}
