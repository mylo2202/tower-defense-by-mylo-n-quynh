package sample;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameStage {
    public static final int HEIGHT = 515;
    public static final int WIDTH = 918;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    public GameStage(){
        intitLizeStage();
        //  createNewGame(menuStage);

    }
    private void intitLizeStage(){
        //menuButton = new ArrayList<MyButton>();
        gamePane=new AnchorPane();
        gameScene=new Scene(gamePane,WIDTH,HEIGHT);
        gameStage=new Stage();
        gameStage.setScene(gameScene);
    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();
        gameStage.show();
    }

}
