package game;

import game.characters.MyLabel;
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
    public static final int HEIGHT = 515;
    public static final int WIDTH = 900;

    public static final  int BUTTON_X= 350;
    public static final  int BUTTON_Y= 190;

    public static final int LOGO_X = 185;
    public static final int LOGO_Y =60;


    private MyBorderPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public MenuGame(){

        mainPane=new MyBorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setAreasCenter();
        mainScene=new Scene(mainPane,WIDTH,HEIGHT);
        mainStage=new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackGround();
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
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/grey_button06.png');";
        MyButton newPlay = new MyButton("NEW GAME",49,190,url);

        addMenuButton(newPlay);
        newPlay.setOnAction(actionEvent -> {
            GameStage gameViewManger= new GameStage();
            gameViewManger.createNewGame(mainStage);
        });
    }
    public void createQuitButton(){
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/grey_button06.png');";
        MyButton quit = new MyButton("QUIT",49,190,url);

        addMenuButton(quit);
        quit.setOnAction(actionEvent -> {
            mainStage.close();
        });
    }
    public void createContinueButton(){
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/grey_button06.png');";
        MyButton continuePlay = new MyButton("CONTINUE",49,190,url);

        addMenuButton(continuePlay);
    }
    private void createBackGround(){
        Image backgroundImage=new Image("/Image/Background/background1.png",WIDTH,HEIGHT,false,true);
        BackgroundImage  background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));

    }
    private  void createLogo(){
        ImageView logo = new ImageView("/Image/Logo/logo3.png");

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

        mainPane.setTop(logo);

        BorderPane.setMargin(logo, new Insets(LOGO_Y, 10, 10, 10));
        BorderPane.setAlignment(logo, Pos.CENTER);
    }



}