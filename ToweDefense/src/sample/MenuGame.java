package sample;

import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.event.EventHandler;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class MenuGame {
    public static final int HEIGHT = 515;
    public static final int WIDTH = 900;

    public static final  int BUTTON_X= 350;
    public static final  int BUTTON_Y= 190;

    public static final int LOGO_X = 185;
    public static final int LOGO_Y =50;

    List<ButtonGame> menuButton;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public MenuGame(){
        menuButton = new ArrayList<ButtonGame>();
        mainPane=new AnchorPane();
        mainScene=new Scene(mainPane,WIDTH,HEIGHT);
        mainStage=new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackGround();
        createLogo();
    }
    private void addMenuButton(ButtonGame button){

        button.setLayoutX(BUTTON_X);
        button.setLayoutY(BUTTON_Y  + menuButton.size()*100);
        menuButton.add(button);
        mainPane.getChildren().add(button);
    }
    public Stage getMainStage(){
        return mainStage;
    }
    public void createButton(){
        createPlayButton();
        createContinueButton();
        createQuitButton();
    }
    public void createPlayButton(){
        ButtonGame newPlay = new ButtonGame("NEW GAME");
        addMenuButton(newPlay);
        newPlay.setOnAction(actionEvent -> {
            GameStage gameViewManger= new GameStage();
            gameViewManger.createNewGame(mainStage);
        });
    }
    public void createQuitButton(){
        ButtonGame quit = new ButtonGame("QUIT");
        addMenuButton(quit);
        quit.setOnAction(actionEvent -> {
            mainStage.close();
        });
    }
    public void createContinueButton(){
        ButtonGame continuePlay = new ButtonGame("CONTINUE");
        addMenuButton(continuePlay);
    }
    private void createBackGround(){
        Image backgroundImage=new Image("/Image/Background/background1.png",WIDTH,HEIGHT,false,true);
        BackgroundImage  background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));

    }
    private  void createLogo(){
        ImageView logo = new ImageView("/Image/Logo/logo3.png");
        logo.setLayoutX(LOGO_X);
        logo.setLayoutY(LOGO_Y);
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
