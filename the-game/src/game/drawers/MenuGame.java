package game.drawers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuGame {
    public static final int HEIGHT = 515;
    public static final int WIDTH = 900;

    public static final int LOGO_X = 185;
    public static final int LOGO_Y = 60;

    private MyBorderPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public MenuGame() {

        mainPane = new MyBorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackGround();
        createLogo();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void createButton() {
        createPlayButton();
        createContinueButton();
        createQuitButton();

    }

    public void createPlayButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton newPlay = new MyButton("NEW GAME", 45, 190, url);

        mainPane.addButton(newPlay);
        newPlay.setOnAction(actionEvent -> {
            GameStage gameViewManger;
            try {
                gameViewManger = new GameStage();
                gameViewManger.createNewGame(mainStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void createQuitButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton quit = new MyButton("QUIT", 45, 190, url);

        mainPane.addButton(quit);
        quit.setOnAction(actionEvent -> {
            mainStage.close();
        });
    }

    public void createContinueButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton continuePlay = new MyButton("CONTINUE", 45, 190, url);

        mainPane.addButton(continuePlay);
    }

    private void createBackGround() {
        Image backgroundImage = new Image("/Image/Background/background1.png", WIDTH, HEIGHT, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));

    }

    private void createLogo() {
        ImageView logo = new ImageView("/Image/Logo/logo3.png");

        logo.setOnMouseEntered(mouseEvent -> logo.setEffect(new DropShadow()));
        logo.setOnMouseExited(mouseEvent -> logo.setEffect(null));

        mainPane.setTop(logo);

        BorderPane.setMargin(logo, new Insets(LOGO_Y, 10, 10, 10));
        BorderPane.setAlignment(logo, Pos.CENTER);
    }
}
