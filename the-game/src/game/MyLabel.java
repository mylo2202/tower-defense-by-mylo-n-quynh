<<<<<<< HEAD:the-game/src/game/MyLabel.java
package game;
=======
package sample;
>>>>>>> b421015a7754a5e36c706c6dd09aaccc9216da04:ToweDefense/src/sample/MyLabel.java

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class MyLabel extends Label {
<<<<<<< HEAD:the-game/src/game/MyLabel.java
    public static final String FONT_PATH="src/UI/Font/kenvector_future.ttf";
    public static final String BACKGROUND_IMAGE="/Image/UI/grey_button06.png";
=======
/*    private final String FONT_PATH="/src/Image/UI/kenvector_future.ttf";

    private String backgroundLabel;//= "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/grey_button06.png');";
    public MyLabel(String text, int h,int w,String url){


    }*/
    private static final String FONT_PATH="src/Image/UI/kenvector_future.ttf";
    private static final String BACKGROUND_IMAGE="/Image/UI/green_button13.png";
>>>>>>> b421015a7754a5e36c706c6dd09aaccc9216da04:ToweDefense/src/sample/MyLabel.java

    public MyLabel(String text){
        setPrefWidth(190);
        setPrefHeight(49);
        setAlignment(Pos.CENTER_LEFT);
        setText(text);
<<<<<<< HEAD:the-game/src/game/MyLabel.java
        setWrapText(true);
        setLabelFont();
=======
        setPadding(new Insets(10,10,10,20) );
>>>>>>> b421015a7754a5e36c706c6dd09aaccc9216da04:ToweDefense/src/sample/MyLabel.java
        BackgroundImage backgroundImage=new BackgroundImage(new Image(BACKGROUND_IMAGE,190,45,false,true),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(backgroundImage));
        setLabelFOnt();
    }
    private void setLabelFOnt(){
        try{
<<<<<<< HEAD:the-game/src/game/MyLabel.java
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),15));
=======
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),18));
>>>>>>> b421015a7754a5e36c706c6dd09aaccc9216da04:ToweDefense/src/sample/MyLabel.java
        } catch (FileNotFoundException e){
            setFont(Font.font("Mongoose", 15));
        }
    }
}
