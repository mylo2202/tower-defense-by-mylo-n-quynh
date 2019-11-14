package game.drawers;

import java.io.*;

import game.GameEntity;
import game.characters.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameStage {
    private AnimationTimer gameTimer;

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private Label Label;

    private MyLabel Money;
    private int point;
    private ImageView [] lifes;
    private int life = 4;
    private TileMap map = new TileMap();
    private boolean play = false;

    private GameEntity gameEntity = new GameEntity();

    public GameStage() throws IOException {
        initialiseStage();
        createMouseListeners();
    }

    private void createMouseListeners() {
        gameScene.setOnMousePressed(mouseEvent -> {

        });
        gameScene.setOnMouseReleased(mouseEvent -> {

        });
    }

    private void initialiseStage()
    {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, map.getSCREEN_WIDTH(), map.getSCREEN_HEIGHT());
        gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage) throws IOException {
        this.menuStage=menuStage;
        this.menuStage.hide();

        map = new TileMap();

        drawPanel();
        createPanelControl();

        map.drawMap(gamePane);

        createButton();
        //createGameLoop();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }

    public Stage getStage(){
        return gameStage;
    }

    public void drawPanel()
    {
        Image panel = new Image("/Image/UI/green_panel.png",
                map.getSCREEN_WIDTH() - map.getGrid()[0].length*map.getSize(), map.getSCREEN_HEIGHT(), false, true);
        ImageView panelView = new ImageView(panel);
        panelView.setLayoutX(map.getGrid()[0].length*map.getSize());
        panelView.setLayoutY(0);
        gamePane.getChildren().add(panelView);
    }

    /*private void createGameLoop(){

    }*/

    public void createButton(){
        buttonStart();
    }

    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",45,190,url);
        Start.setLayoutX(map.getGrid()[0].length*map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length*map.getSize())/2 - 95);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {
            Enemy e = null;
            try {
                e = new NormalEnemy();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            assert e != null;
            e.enemyMove();
            gamePane.getChildren().add(e.getEnemyView());
            Enemy finalE = e;
            finalE.enemyMove();
            gameTimer = new AnimationTimer() {
                long timer = System.nanoTime();
                @Override
                public void handle(long now) {
                    if(now - timer >= 1*1e9)
                    {
                        System.out.println((finalE.getEnemyView().getTranslateX() + 32) + " " +
                                (finalE.getEnemyView().getTranslateY() + 32));
                        timer = now;
                    }
                }
            };
            gameTimer.start();
            play = true;
        });

        gamePane.getChildren().add(Start);
    }

    public void createPanelControl(){
        lifes= new ImageView[4];
        for (int i=0; i<4; ++i){
            lifes[i]= new ImageView("/Image/UI/heart1.png");
            lifes[i].setLayoutX(map.getGrid()[0].length*map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length*map.getSize())/2 - 95 + (i*48));
            lifes[i].setLayoutY(128);
            gamePane.getChildren().add(lifes[i]);
        }
        Money =new MyLabel("MONEY : 0050");
        Money.setLayoutX(map.getGrid()[0].length*map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length*map.getSize())/2 - 95);
        Money.setLayoutY(64);
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