package game;

import game.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.event.EventHandler;


public class MenuGame {
    private static final int HEIGHT = 515;
    private static final int WIDTH = 900;

    private static final  int BUTTON_X= 350;
    private static final  int BUTTON_Y= 190;

    private static final int LOGO_X = 185;
    private static final int LOGO_Y =60;


    private MyBorderPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public MenuGame(){

        mainPane=new MyBorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setAreasCenter();
        mainScene=new Scene(mainPane,WIDTH,HEIGHT);
        mainStage=new Stage();
        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
        createButton();
        createBackground();
        createLogo();
    }

    private void addMenuButton(MyButton button){

        mainPane.addButton(button);
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
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton newPlay = new MyButton("NEW GAME",45,190,url);

        addMenuButton(newPlay);
        newPlay.setOnAction(actionEvent -> {
            GameStage gameViewManger= new GameStage();
            gameViewManger.createNewGame(mainStage);
        });
    }

    public void createQuitButton(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton quit = new MyButton("QUIT",45,190,url);

        addMenuButton(quit);
        quit.setOnAction(actionEvent -> {
            mainStage.close();
        });
    }

    public void createContinueButton(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton continuePlay = new MyButton("CONTINUE",45,190,url);

        addMenuButton(continuePlay);
    }

    private void createBackground(){
        Image backgroundImage=new Image("/Image/Background/background1.png",WIDTH,HEIGHT,false,true);
        BackgroundImage  background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));

    }

    private  void createLogo(){
        ImageView logo = new ImageView("/Image/Logo/logo3.png");

        logo.setOnMouseEntered(mouseEvent -> logo.setEffect(new DropShadow()));
        logo.setOnMouseExited(mouseEvent -> logo.setEffect(null));

        mainPane.setTop(logo);

        BorderPane.setMargin(logo, new Insets(LOGO_Y, 10, 10, 10));
        BorderPane.setAlignment(logo, Pos.CENTER);
    }
}