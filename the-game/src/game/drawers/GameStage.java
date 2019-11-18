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
        createTower();
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

    private void createTower() throws IOException {
        NormalTower normalTower = new NormalTower();
        normalTower.getTowerView().setLayoutX(240);
        normalTower.getTowerView().setLayoutY(320);
        gamePane.getChildren().add(normalTower.getTowerView());
    }

    public void createButton(){
        buttonStart();
    }

    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",45,190,url);
        Start.setLayoutX(map.getGrid()[0].length*map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length*map.getSize())/2 - 95);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {
            gameTimer = new AnimationTimer()
            {
                int difficulty = 10;
                long timer = System.nanoTime();
                    @Override
                    public void handle(long now) {
                        Enemy enemy;
                        if(now - timer >= 0.25*1e9)
                        {
                            if(difficulty > 0)
                            {
                                try {
                                    //gameEntity.generateEnemy(enemy, 10);
                                    gameEntity.getEnemyList().add(enemy = new SmallerEnemy());
                                    assert false;
                                    enemy.enemyMove();
                                    gamePane.getChildren().add(enemy.getEnemyView());
                                    difficulty -= enemy.getLevel();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            if(!gameEntity.getEnemyList().isEmpty())
                            {
                                System.out.println(gameEntity.getEnemyList().get(0).getEnemyView().getTranslateX() + " " + gameEntity.getEnemyList().get(0).getEnemyView().getTranslateY());

                                for(int i = 0; i < gameEntity.getEnemyList().size(); i++)
                                {
                                    if(gameEntity.checkRemoveEnemy(i))
                                    {
                                        gamePane.getChildren().remove(gameEntity.getEnemyList().get(i).getEnemyView());
                                        gameEntity.removeEnemy(i);
                                    }
                                }
                            }
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