package game.drawers;

import game.Music;
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
    boolean setMusic = true;
    private String turnMusic = "ON";
    private GameStage gameStage;
    private Music music;

    public MenuGame() throws IOException {

        mainPane = new MyBorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        gameStage = new GameStage();
        music = gameStage.getMusic();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackGround();
        createLogo();
        music.getMediaBackground().play();

    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Music getMusic() {
        return music;
    }

    public void createButton() {
        createPlayButton();
        createSettingsButton();
        createQuitButton();

    }

    public void createPlayButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton newPlay = new MyButton("NEW GAME", 45, 190, url);
        mainPane.addButton(newPlay);
        newPlay.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();

            try {
                gameStage.createNewGame(mainStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        newPlay.setOnMouseReleased(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().stop();
        });
    }

    public void createQuitButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton quit = new MyButton("QUIT", 45, 190, url);

        mainPane.addButton(quit);
        quit.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
            System.out.println(music.isPlayMusic());
            mainStage.close();
        });
    }

    public void createSettingsButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";

        MyButton musicPlay = new MyButton("Music : " + turnMusic, 45, 190, url);
        mainPane.addButton(musicPlay);

        musicPlay.setOnMousePressed(actionEvent -> {
            music.getMediaButton().play();

            music.setPlayMusic(!music.isPlayMusic());

            if (music.isPlayMusic()) turnMusic = "ON";
            else turnMusic = "OFF";
            // System.out.println(music.isPlayMusic());
            music.setMusic();
            musicPlay.setText("Music : " + turnMusic);
        });


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
