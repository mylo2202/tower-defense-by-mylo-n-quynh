package sample;


import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class viewManager {
    public static final int HEIGHT = 515;
    public static final int WIDTH = 918;
    private AnchorPane mainPane;
    private Scene mainScene;

    private Stage mainStage;
    List<MyButton> menuButton;
    public viewManager(){
        menuButton = new ArrayList<MyButton>();
        mainPane=new AnchorPane();
        mainScene=new Scene(mainPane,WIDTH,HEIGHT);
        mainStage=new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackGround();
        createLogo();
    }
    private void addMenuButton(MyButton button){
        button.setLayoutX(364);
        button.setLayoutY(233  + menuButton.size()*100);
        menuButton.add(button);
        mainPane.getChildren().add(button);
    }
    public Stage getMainStage(){
        return mainStage;
    }
    public void createButtons(){
        createContinueButton();
        createPlayButton();
    }
    public void createPlayButton(){
        MyButton newPlay =new MyButton("NEW GAME");
        addMenuButton(newPlay);
    }
    public void createContinueButton(){
        MyButton continuePlay =new MyButton("CONTINUE");
        addMenuButton(continuePlay);
    }
    private void createBackGround(){
        Image backgroundImage=new Image("/UI/background2.png",1070,515,false,true);
        BackgroundImage  background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));

    }
    private  void createLogo(){
        ImageView logo = new ImageView("/UI/logo3.png");
        logo.setLayoutX(200);
        logo.setLayoutY(100);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(new DropShadow());
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }



}
