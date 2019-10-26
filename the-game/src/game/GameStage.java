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

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private Label Label;

    public GameStage(){
        initialiseStage();
    }
    private void initialiseStage(){

        gamePane=new AnchorPane();

        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage=new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);




    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();
        //createLabel();
        createButton();
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
    public void labelChoose(){
        ImageView i=new ImageView("/Image/UI/green_button13.png");
        MyLabel label=new MyLabel("Choose Tower");
        label.setLayoutX(800);
        label.setLayoutY(64);
        gamePane.getChildren().add(Label);
    }
    public void createLabel(){
        labelChoose();
    }
    public void createButton(){
        buttonStart();
    }
    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",49,190,url);
        Start.setLayoutX(800);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {
            /*Enemy e =new Enemy("/Image/Enemy/towerDefense_tile246.png");
            e.setLayoutX(0);
            e.setLayoutY(64);;
            e.enemyMove();
            gamePane.getChildren().add(e.getEnemy());*/
        });

        gamePane.getChildren().add(Start);
    }


}