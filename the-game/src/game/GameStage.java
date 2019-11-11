package game;

import java.io.*;
import game.characters.TileMap;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameStage {
    private final int HEIGHT = 720;
    private final int WIDTH = 1280;

    private AnimationTimer gameTimer;

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private Label Label;

    private MyLabel Money;
    private int point;
    private ImageView [] lifes;
    private int life=4;
    private TileMap map;
    private boolean play=false;

    public GameStage(){
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
        gameScene = new Scene(gamePane, WIDTH, HEIGHT);
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
        createGameLoop();

        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }

    public Stage getStage(){
        return gameStage;
    }

    public void drawPanel()
    {
        Image panel = new Image("/Image/UI/green_panel.png", WIDTH - map.getGrid()[0].length*map.getSize(), HEIGHT, false, true);
        ImageView panelView = new ImageView(panel);
        panelView.setLayoutX(map.getGrid()[0].length*map.getSize());
        panelView.setLayoutY(0);
        gamePane.getChildren().add(panelView);
    }

    private void createGameLoop(){
        {
            gameTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                }
            };
            gameTimer.start();
        }
    }

    public void createButton(){
        buttonStart();
    }

    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",45,190,url);
        Start.setLayoutX(map.getGrid()[0].length*map.getSize() + (WIDTH - map.getGrid()[0].length*map.getSize())/2 - 95);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {
            /*Enemy e = new BossEnemy("/Image/Enemy/bossEnemy.png");
            e.setLayoutX(0);
            e.setLayoutY(64);
            e.enemyMove();
            gamePane.getChildren().add(e.getEnemyView());*/
            play = true;
        });

        gamePane.getChildren().add(Start);
    }

    private void createPanelControl(){
        lifes= new ImageView[4];
        for (int i=0; i<4; ++i){
            lifes[i]= new ImageView("/Image/UI/heart1.png");
            lifes[i].setLayoutX(map.getGrid()[0].length*map.getSize() + (WIDTH - map.getGrid()[0].length*map.getSize())/2 - 95 + (i*48));
            lifes[i].setLayoutY(128);
            gamePane.getChildren().add(lifes[i]);
        }
        Money =new MyLabel("MONEY : 0050");
        Money.setLayoutX(map.getGrid()[0].length*map.getSize() + (WIDTH -map.getGrid()[0].length*map.getSize())/2 - 95);
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