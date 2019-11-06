package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.control.Button;


public class MyButton extends Button {
    private final String FONT_PATH="src/Image/UI/kenvector_future.ttf";

    private String BUTTON_PRESS_STYLE;
    private String BUTTON_FREE_STYLE;
    private int h;
    private int w;
    public MyButton (String text,int h,int w, String url){
        BUTTON_PRESS_STYLE = url;
        BUTTON_FREE_STYLE = url;
        setText(text);
        setButtonFont();
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(h );
        setPrefWidth(w);
        intitButtonListener();

    }

    public void setBUTTON_FREE_STYLE(String url) {
        this.BUTTON_FREE_STYLE = url;
    }


    public void setButtonFont(){
        try{

            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        } catch (FileNotFoundException e){
            setFont(Font.font("Mongoose", 23));
        }
    }
    private void setButtonPressStyle(){
        setStyle(BUTTON_PRESS_STYLE);
        setPrefHeight(h);

        setLayoutY(getLayoutY()+2);
    }
    private void setButtonReleasedStyle(){
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(h);
        setLayoutY(getLayoutY()-2);
    }
    private void intitButtonListener(){
        setOnMousePressed(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                setButtonPressStyle();
            }
        });
        setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                setButtonReleasedStyle();
            }
        });
        setOnMouseEntered(mouseEvent -> setEffect(new DropShadow()));
        setOnMouseExited(mouseEvent -> setEffect(null));
    }


}
