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
import java.util.Iterator;

public class GameStage {

    private AnimationTimer startTimer;
    private AnimationTimer towerTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private MyLabel Money;

    private Image hammer = new Image("/Image/Tower/Hammer.png");
    private ImageView life;
    private MyLabel lifes;
    private TileMap map = new TileMap();

    private GameField gameField = new GameField();
    private boolean play = true;

    public GameStage() throws IOException {
        initialiseStage();

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

        life = new ImageView("/Image/UI/heart1.png");
        life.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 80);
        life.setLayoutY(128);
        gamePane.getChildren().add(life);
        lifes = gameField.getLife();
        lifes.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 40);
        lifes.setLayoutY(128);
        gamePane.getChildren().add(lifes);
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


    public void createNewGame(Stage menuStage) throws IOException {
        this.menuStage = menuStage;
        this.menuStage.hide();

        map = new TileMap();
        drawPanel();

        map.drawMap(gamePane);
        createButton();
        gameLoop();

        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }

    public void gameLoop() {
        gameField.getTowerList().forEach(tower -> {
                    tower.setPos(new Point2D(tower.getView().getTranslateX(), tower.getView().getTranslateY()));
                }
        );
        towerTimer = new AnimationTimer() {
            long timer1 = System.nanoTime();

            @Override
            public void handle(long now) {
                Iterator<Tower> towerIterator = gameField.getTowerList().iterator();
                while (towerIterator.hasNext()) {
                    Tower tower = towerIterator.next();
                    tower.update(tower.targetEnemy(gameField.getEnemyList()));

                    Iterator<Bullet> iterator = tower.getBulletList().iterator();
                    while (iterator.hasNext()) {
                        Bullet bullet = iterator.next();
                        bullet.update(tower.targetEnemy(gameField.getEnemyList()));
                        Enemy enemy1 = tower.targetEnemy(gameField.getEnemyList());
                        double range = Math.sqrt(Math.pow(bullet.getView().getTranslateX() - bullet.getPos().getX(), 2)
                                + Math.pow(bullet.getView().getTranslateY() - bullet.getPos().getY(), 2));
                        if (range >= tower.getRadius() || gameField.getEnemyList().isEmpty()) {
                            gamePane.getChildren().remove(bullet.getView());
                            tower.getBulletList().remove(iterator);
                        }
                        if (bullet.isColliding(enemy1)) {
                            gamePane.getChildren().remove(bullet.getView());
                            tower.getBulletList().remove(iterator);
                            enemy1.removeHitPoints(tower.getAttackDamage());
                            //  if (enemy1.getHitPoints() <= 0) tower.targetEnemy(gameField.getEnemyList()).setDead(true);
                            if (enemy1.isDead()) gameField.updateMoney(tower.targetEnemy(gameField.getEnemyList()));

                        }

                    }


                }
                if (now - timer1 >= 0.25 * 1e9) {

                    gameField.getTowerList().forEach(tower -> {
                        if (!gameField.getEnemyList().isEmpty() && tower.targetEnemy(gameField.getEnemyList()).distance(tower) <= tower.getRadius()) {
                            tower.createBullet(new Bullet(tower));
                            gamePane.getChildren().add(tower.getBullet().getView());
                        }
                    });

                    timer1 = now;
                }
                if (!gameField.getEnemyList().isEmpty()) {
                    //  System.out.println(gameField.getEnemyList().get(0).getView().getTranslateX() + " " + gameField.getEnemyList().get(0).getView().getTranslateY());

                    for (int i = 0; i < gameField.getEnemyList().size(); i++) {
                        if (gameField.getEnemyList().get(i).hasReachedGoal()) {

                        }
                        if (gameField.checkRemoveEnemy(i)) {
                            gamePane.getChildren().remove(gameField.getEnemyList().get(i).getView());
                            gameField.removeEnemy(i);

                        }
                    }
                }
            }
        };
        towerTimer.start();
    }
    public void buttonStart() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start = new MyButton("START", 45, 190, url);
        Start.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        Start.setLayoutY(640);

        Start.setOnAction(actionEvent -> {
            if (play == true) {
                startTimer = new AnimationTimer() {
                    int difficulty = 10;
                    long timer = System.nanoTime();
                    @Override
                    public void handle(long now) {
                        Enemy enemy = null;
                        if (now - timer >= .25 * 1e9) {
                            if (difficulty > 0) {
                                try {
                                    gameField.getEnemyList().add(enemy = new SmallerEnemy());
                                    assert false;
                                    enemy.enemyMove().play();

                                    gamePane.getChildren().add(enemy.getView());
                                    difficulty -= enemy.getLevel();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            timer = now;
                        }
                    }
                };

                startTimer.start();
                //   play = false;
            }
        });

        gamePane.getChildren().add(Start);
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