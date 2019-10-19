package sample;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameStage {
    public static final int HEIGHT = 515;
    public static final int WIDTH = 1010;

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    public GameStage(){
        intitLizeStage();

    }
    private void intitLizeStage(){
        //menuButton = new ArrayList<MyButton>();
        gamePane=new AnchorPane();
        gameScene=new Scene(gamePane,WIDTH,HEIGHT);
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
        Image backgroundImage=new Image("/Image/Background/background2.png",WIDTH,HEIGHT,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        gamePane.setBackground(new Background(background));

    }


}
