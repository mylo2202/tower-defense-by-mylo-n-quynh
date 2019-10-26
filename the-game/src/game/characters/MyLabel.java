package game.characters;

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
    public static final String FONT_PATH="/src/UI/Font/kenvector_future.ttf";
    public static final String BACKGROUND_IMAGE="/Image/UI/green_button06.png";

    public MyLabel(String text){
        setPrefWidth(256);
        setPrefHeight(256);
        setAlignment(Pos.CENTER);
        setText(text);
        setWrapText(true);
        setLabelFont();
        BackgroundImage backgroundImage=new BackgroundImage(new Image(BACKGROUND_IMAGE,256,256,false,true),
                BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
    }
    private void setLabelFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        } catch (FileNotFoundException e){
            setFont(Font.font("Mongoose", 23));
        }
    }

}
