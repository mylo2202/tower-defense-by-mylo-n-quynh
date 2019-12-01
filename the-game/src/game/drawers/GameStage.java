package game.drawers;

import game.GameField;
import game.characters.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage {

    private AnimationTimer gameTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private MyLabel Money;

    private Image hammer = new Image("/Image/Tower/Hammer.png");
    private ImageView[] lifes;
    private int life = 4;
    private TileMap map = new TileMap();

    private GameField gameField = new GameField();

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

    private void initialiseStage() {
        gamePane = new AnchorPane();

        gameScene = new Scene(gamePane, map.getSCREEN_WIDTH(), map.getSCREEN_HEIGHT());
        gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
    }



    public Stage getStage() {
        return gameStage;
    }

    private EventHandler<MouseEvent> MouseReleased = new EventHandler<>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            gameScene.setCursor(Cursor.DEFAULT);
        }

    };

    public void drawPanel() {
        Image panel = new Image("/Image/UI/green_panel.png",
                map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize(), map.getSCREEN_HEIGHT(), false, true);
        ImageView panelView = new ImageView(panel);
        panelView.setLayoutX(map.getGrid()[0].length * map.getSize());
        panelView.setLayoutY(0);
        gamePane.getChildren().add(panelView);

        lifes = new ImageView[4];
        for (int i = 0; i < 4; ++i) {
            lifes[i] = new ImageView("/Image/UI/heart1.png");
            lifes[i].setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95 + (i * 48));
            lifes[i].setLayoutY(128);
            gamePane.getChildren().add(lifes[i]);
        }
        Money = gameField.getMoney();
        Money.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        Money.setLayoutY(64);
        gamePane.getChildren().add(Money);
    }

    public void createButton() {
        buttonStart();
        machineTowerButton();
        normalTowerButton();
        sniperTowerButton();
    }

    public void removeLife() {
        gamePane.getChildren().remove(lifes[life - 1]);
        life--;
        if (life == 0) {
            gameTimer.stop();
        }
    }

    public void createNewGame(Stage menuStage) throws IOException {
        this.menuStage = menuStage;
        this.menuStage.hide();

        map = new TileMap();
        drawPanel();

        map.drawMap(gamePane);
        createButton();

        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }

    public void buttonStart() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start = new MyButton("START", 45, 190, url);
        Start.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {

            gameField.getTowerList().forEach(machineGunTower -> machineGunTower.setPos(new Point2D(machineGunTower.getView().getTranslateX(),
                    machineGunTower.getView().getTranslateY())));

            //bullet.setDirection(new Point2D( -1,-1));
            gameTimer = new AnimationTimer() {
                int difficulty = 10;
                long timer = System.nanoTime();
                long timer1 = System.nanoTime();
                @Override
                public void handle(long now) {

                    Enemy enemy;
                    Bullet bullet;
                    if (now - timer >= 0.25 * 1e9) {

                        if (difficulty > 0) {
                            try {
                                //gameEntity.generateEnemy(enemy, 10);
                                gameField.getEnemyList().add(enemy = new SmallerEnemy());
                                assert false;
                                enemy.enemyMove();

                                gamePane.getChildren().add(enemy.getView());
                                difficulty -= enemy.getLevel();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (!gameField.getEnemyList().isEmpty()) {
                            //   System.out.println(gameField.getEnemyList().get(0).getView().getTranslateX() + " " + gameField.getEnemyList().get(0).getView().getTranslateY());

                            for (int i = 0; i < gameField.getEnemyList().size(); i++) {

                                if (gameField.checkRemoveEnemy(i)) {
                                    gamePane.getChildren().remove(gameField.getEnemyList().get(i).getView());
                                    gameField.removeEnemy(i);
                                }
                            }
                        }


                        timer = now;
                    }


                    gameField.getTowerList().forEach(tower -> {
                        tower.update(gameField.getEnemyList().get(0));
                        tower.setSlope(gameField.getEnemyList().get(0));
                        gameField.getBulletList().forEach(bullet1 -> {
                            bullet1.update(gameField.getEnemyList().get(0));
                        });
                    });
                    if (now - timer1 >= 0.25 * 1e9) {
                        bullet = new Bullet(gameField.getTowerList().get(0));
                        System.out.println(bullet.getDirection());
                        gameField.getBulletList().add(bullet);
                        gamePane.getChildren().add(bullet.getView());

                        timer1 = now;
                    }

                }
            };

            gameTimer.start();


        });

        gamePane.getChildren().add(Start);
    }

    public EventHandler<MouseEvent> buildSniperTower() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        };
        return eventHandler;
    }

    public EventHandler<MouseEvent> buildNormalTower() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        };
        return eventHandler;
    }

    public void machineTowerButton() {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/machineGunTowerButton.png');";
        MyButton machine = new MyButton("", 64, 64, url);
        machine.setLayoutX(1015);
        machine.setLayoutY(200);
        gamePane.getChildren().add(machine);

        machine.setOnAction(actionEvent -> {
            gameField.setBuild(true);
            gameScene.setCursor(new ImageCursor(hammer));
            try {
                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, map, new MachineGunTower()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);

        });

    }

    public void normalTowerButton() {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/normalTowerButton.png');";
        MyButton normal = new MyButton("", 64, 64, url);
        normal.setLayoutX(1089);
        normal.setLayoutY(200);
        gamePane.getChildren().add(normal);

        normal.setOnAction(actionEvent -> {
            gameField.setBuild(true);
            gameScene.setCursor(new ImageCursor(hammer));
            try {
                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, map, new NormalTower()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);

        });

    }

    public void sniperTowerButton() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/sniperTowerButton.png');";
        MyButton Sniper = new MyButton("", 64, 64, url);
        Sniper.setLayoutX(1163);
        Sniper.setLayoutY(200);
        gamePane.getChildren().add(Sniper);

        Sniper.setOnAction(actionEvent -> {
            gameField.setBuild(true);
            gameScene.setCursor(new ImageCursor(hammer));
            try {
                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, map, new SniperTower()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);

        });

    }
}