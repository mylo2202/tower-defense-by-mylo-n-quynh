package game.drawers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyLabel extends Label {

    private static final String FONT_PATH = "src/Image/UI/Radiance-Bold.otf";
    private static final String BACKGROUND_IMAGE = "/Image/UI/green_button13.png";

    public MyLabel(String text) {
        setPrefWidth(190);
        setPrefHeight(45);
        setAlignment(Pos.CENTER);
        setText(text);
        setPadding(new Insets(10, 10, 10, 20));
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, 190, 45, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setLabelFont();
    }

    public MyLabel(String text, String url, int h, int w) {
        setPrefWidth(w);
        setPrefHeight(h);
        setAlignment(Pos.CENTER);
        setText(text);
        setPadding(new Insets(10, 10, 10, 20));
        BackgroundImage backgroundImage = new BackgroundImage(new Image(url, w, h, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setLabelFont();
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 18));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Mongoose", 15));
        }
    }

    public void setLabelFont(String url, int size) {
        try {
            setFont(Font.loadFont(new FileInputStream(url), size));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Mongoose", size));
        }
    }
}