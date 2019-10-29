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

    private GameSubScene panelScene;
    private MyLabel pointLabel;
    private MyLabel Money;
    private int point;
    private ImageView [] lifes;
    private int life=4;
    private Enemy e;
    private boolean play=false;

    public void ShowSubScene(GameSubScene subScene){
        if(panelScene!= null){
            panelScene.moveSubScene();
        }
        subScene.moveSubScene();
        panelScene=subScene;
    }

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
       // createSubScene();
        createPanelControl();
        createEnemy();
        createButton();
        createGameLoop();


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
                Image image =new Image("/Image/Map/" + grid[i][j] +  ".png",64,64,false,true);
                ImageView imageView =new ImageView(image);
                imageView.setLayoutX(j*64);
                imageView.setLayoutY(i*64);
                gamePane.getChildren().addAll(imageView);
            }
        }
        ImageView panel =new ImageView("/Image/UI/green_panel.png");
        panel.setLayoutX(768);
        panel.setLayoutY(0);
        gamePane.getChildren().add(panel);

    }
    public void createSubScene(){
        createChooseSubSence();

    }
    public void createLabel(){

        Label label = new Label("My Label");
        label.setLayoutX(768);
        label.setLayoutY(0);
        gamePane.getChildren().add(label);
    }
    public void createEnemy(){
        e =new Enemy("/Image/Enemy/towerDefense_tile246.png");
        e.setLayoutX(-32);
        e.setLayoutY(64);;

        if(e.getPosX()< 10 && e.getPosY()==608) removeLife();

    }
    public void enemyMove(){



    }
    private void createGameLoop(){
         {
            gameTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {

                    e.enemyMove();
                }
            };
            gameTimer.start();
        }
    }
    public void createButton(){
        buttonStart();
        buttonTower1();
        /*buttonToer2();
        buttonTower3();
        buttonTowr4();*/


    }
    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",45,190,url);
        Start.setLayoutX(800);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {

            play= true;
        });

        gamePane.getChildren().add(Start);

    }
    public void buttonTower1(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/Tower/TowerDefense_tile203.png');";
        MyButton Tower1 =new MyButton("  ",64,64, url);
        Tower1.setOnAction(actionEvent -> {

        });
        Tower1.setLayoutX(788);
        Tower1.setLayoutY(190);
        gamePane.getChildren().add(Tower1);
    }
    private void createChooseSubSence() {
        panelScene= new GameSubScene();
        gamePane.getChildren().add(panelScene);
        panelScene.moveSubScene();


    }
    private void createPanelControl(){
        pointLabel=new MyLabel("POINT : 00");
        pointLabel.setLayoutX(768 +32);
        pointLabel.setLayoutY(64-32);
        gamePane.getChildren().add(pointLabel);
        lifes= new ImageView[4];
        for (int i=0;i<4;++i){
            lifes[i]= new ImageView("/Image/UI/heart1.png");
            lifes[i].setLayoutX(768+43+(i*45));
            lifes[i].setLayoutY(64+45+8-32);
            gamePane.getChildren().add(lifes[i]);
        }
        Money =new MyLabel("MONEY : 0050");
        Money.setLayoutX(768+32);
        Money.setLayoutY(64+45+24);
        gamePane.getChildren().add(Money);

    }
    public void removeLife(){
        gamePane.getChildren().remove(lifes[ life ]);
        life--;
        if(life == 0 ) {
            gameTimer.stop();
        }
    }
}
