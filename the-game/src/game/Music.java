package game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Music {
    private final String PATH_MEDIA_BUTTON = "src//Sound//click2.mp3";
    private final String PATH_MEDIA_BACKGROUND = "src/Sound/Watery Graves- Laura Shigihara.mp3";
    private final String PATH_MEDIA_BULLET = "src/Sound/bullet.mp3";
    private final String PATH_MEDIA_ENEMY = "src/Sound/enemy.mp3";
    private final String PATH_MEDIA_GAMEOVER = "src/Sound/lost.mp3";
    private MediaPlayer mediaBullet;
    private MediaPlayer mediaButton;
    private MediaPlayer mediaBackground;
    private MediaPlayer mediaEnemyHasGoal;
    private MediaPlayer mediaGameOver;
    private boolean playMusic = true;

    public Music() {
        mediaEnemyHasGoal = new MediaPlayer(new Media(new File(PATH_MEDIA_ENEMY).toURI().toString()));
        mediaButton = new MediaPlayer(new javafx.scene.media.Media(new File(PATH_MEDIA_BUTTON).toURI().toString()));
        mediaBackground = new MediaPlayer(new javafx.scene.media.Media(new File(PATH_MEDIA_BACKGROUND).toURI().toString()));
        mediaGameOver = new MediaPlayer(new Media(new File(PATH_MEDIA_GAMEOVER).toURI().toString()));
        mediaBackground.setOnEndOfMedia(() -> mediaBackground.seek(Duration.ZERO));
        mediaBullet = new MediaPlayer(new javafx.scene.media.Media(new File(PATH_MEDIA_BULLET).toURI().toString()));
    }

    public MediaPlayer getMediaGameOver() {

        return mediaGameOver;
    }

    public MediaPlayer getMediaBullet() {
        return mediaBullet;
    }

    public void setMediaBullet(MediaPlayer mediaBullet) {
        this.mediaBullet = mediaBullet;
    }

    public MediaPlayer getMediaButton() {
        return mediaButton;
    }

    public void setMediaButton(MediaPlayer mediaButton) {
        this.mediaButton = mediaButton;
    }

    public MediaPlayer getMediaBackground() {
        //  System.out.println("8888");
        return mediaBackground;
    }

    public void setMediaBackground(MediaPlayer mediaBackground) {
        this.mediaBackground = mediaBackground;
    }

    public MediaPlayer getMediaEnemyHasGoal() {
        return mediaEnemyHasGoal;
    }

    public void setMusic() {
        if (!playMusic) {
            getMediaBackground().stop();
            getMediaButton().stop();
            getMediaGameOver().stop();
            getMediaBullet().stop();
            getMediaEnemyHasGoal().stop();
        } else {
            getMediaBackground().play();
            getMediaButton().play();

        }
    }

    public boolean isPlayMusic() {
        return playMusic;
    }

    public void setPlayMusic(boolean Boolean) {
        this.playMusic = Boolean;
    }

}

