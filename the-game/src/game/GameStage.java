package game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameStage {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    private MyBorderPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    public GameStage(){
        intitLizeStage();

    }
    private void intitLizeStage(){

        gamePane=new MyBorderPane();
        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage=new Stage();
        gameStage.setScene(gameScene);
        createBackGround();
    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }
    private void createBackGround(){
        Image backgroundImage=new Image("/Image/Background/background2.png", WIDTH, HEIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        gamePane.setBackground(new Background(background));

    }


}
