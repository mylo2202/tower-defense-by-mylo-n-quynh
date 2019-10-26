package game;

import game.characters.MyLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Font;

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
    private Label Label;

    public GameStage(){
        initialiseStage();
    }
    private void initialiseStage(){

        gamePane=new MyBorderPane();

        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage=new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);




    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();
        createLabel();
        tileMap();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }
    public Stage getStage(){
        return gameStage;
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
    public void createLabel(){


        MyLabel  label = new MyLabel("My Label");
        
        label.setLayoutX(768);
        label.setLayoutY(256);
        gamePane.addLabel(label);
    }

}
