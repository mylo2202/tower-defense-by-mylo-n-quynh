package game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class GameStage {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    private int[][] grid ={
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private MyBorderPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    public GameStage(){
        initialiseStage();

    }
    private void initialiseStage(){
        gamePane=new MyBorderPane();
        gameStage=new Stage();
        gameStage.setFullScreen(false);
        gameStage.setResizable(false);
        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage.setScene(gameScene);
        tileMap();
    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }

    public void tileMap()
    {
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 12; j++)
            {
                Image image =new Image("/Image/PNG/" + grid[i][j] +  ".png",64,64,false,true);
                ImageView imageView =new ImageView(image);
                imageView.setLayoutX(j*64);
                imageView.setLayoutY(i*64);
                gamePane.getChildren().addAll(imageView);
            }
        }
    }
}
