package  sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.security.PrivateKey;


public class GameStage {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private  AnimationTimer gameTimer;

    private int[][] grid ={
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0,0,0,0,0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0,0,0,0,0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0,0,0,0,0},
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0,0,0,0,0},
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0},
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0,0,0,0,0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0,0,0,0,0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0,0,0,0,0},
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0,0,0,0,0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0,0}
    };

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private Label Label;



    public GameStage(){
        initialiseStage();
        createMouseListeners();

    }

    private void createMouseListeners() {
        gameScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        gameScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }

    private void initialiseStage(){
        gamePane=new AnchorPane();
        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage=new Stage();
        gameStage.setResizable(true);
        gameStage.setScene(gameScene);

    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();

        tileMap();
        createButton();
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
            for(int j = 0; j < 16; j++)
            {
                Image image =new Image("/Image/Map/" + grid[i][j] +  ".png",64,64,false,true);
                ImageView imageView =new ImageView(image);
                imageView.setLayoutX(j*64);
                imageView.setLayoutY(i*64);
                gamePane.getChildren().addAll(imageView);
            }
        }

    }


    public void createButton(){
        buttonStart();

    }
    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",45,190,url);
        Start.setLayoutX(800);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {

        });

        gamePane.getChildren().add(Start);

    }

}
