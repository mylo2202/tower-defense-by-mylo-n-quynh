package game.drawers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
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
    String s = "OFF";
    private MediaPlayer mediaButton;
    private MediaPlayer mediaPlayer;

    public Stage getMainStage() {
        return mainStage;
    }

    public MenuGame() {

        mainPane = new MyBorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackGround();
        createLogo();
        String musicFile = "src//Sound//click2.mp3";
        String path = "src/Sound/menuMedia.mp3";

        mediaButton = new MediaPlayer(new Media(new File(musicFile).toURI().toString()));
        mediaPlayer = new MediaPlayer(new Media(new File(path).toURI().toString()));
        mediaPlayer.play();

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

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
            mediaButton.play();

            GameStage gameStage;
            try {
                gameStage = new GameStage();
                gameStage.createNewGame(mainStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean isSetMusic() {
        return setMusic;
    }

    public void setSetMusic(boolean setMusic) {
        this.setMusic = setMusic;
    }

    public void Music(boolean setMusic) {
        if (setMusic == false) {
            s = "ON";
            mediaPlayer.stop();
            mediaButton.stop();
        } else {
            s = "OFF";
            mediaPlayer.play();
            mediaButton.play();
        }
    }

    public void createQuitButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton quit = new MyButton("QUIT", 45, 190, url);

        mainPane.addButton(quit);
        quit.setOnAction(actionEvent -> {
            mediaButton.play();
            mainStage.close();
        });
    }

    public void createSettingsButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";

        MyButton musicPlay = new MyButton("Music : " + s, 45, 190, url);
        mainPane.addButton(musicPlay);

        musicPlay.setOnMousePressed(actionEvent -> {
            setSetMusic(!setMusic);
            Music(setMusic);
            musicPlay.setText("Music : " + s);
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
