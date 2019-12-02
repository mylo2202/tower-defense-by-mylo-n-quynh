package game.drawers;

import game.GameField;
import game.Music;
import game.characters.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
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
    private MySubScene[] infoTower = new MySubScene[3];

    private GameField gameField = new GameField();
    private Music music = gameField.getMusic();
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
            music.getMediaButton().stop();

        }

    };

    public Music getMusic() {
        return music;
    }

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

    public void createButton() throws IOException {
        buttonStart();
        machineTowerButton();
        normalTowerButton();
        sniperTowerButton();
        // hfhjf();
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
            long timer2 = System.nanoTime();
            long timer3 = System.nanoTime();

            @Override
            public void handle(long now) {
                Iterator<Tower> towerIterator = gameField.getTowerList().iterator();
                while (towerIterator.hasNext()) {
                    Tower tower = towerIterator.next();
                    tower.update(tower.targetEnemy(gameField.getEnemyList()));

                    for (int i = 0; i < tower.getBulletList().size(); i++) {

                        tower.getBulletList().get(i).update(tower.targetEnemy(gameField.getEnemyList()));

                        Enemy enemy1 = tower.targetEnemy(gameField.getEnemyList());

                        double range = Math.sqrt(Math.pow(tower.getBulletList().get(i).getView().getTranslateX() - tower.getBulletList().get(i).getPos().getX(), 2)
                                + Math.pow(tower.getBulletList().get(i).getView().getTranslateY() - tower.getBulletList().get(i).getPos().getY(), 2));
                        if (range >= tower.getAttackRange() || gameField.getEnemyList().isEmpty()) {
                            //   System.out.println(bullet.getView().getTranslateX()+"     "+bullet.getView().getTranslateY());

                            gamePane.getChildren().remove(tower.getBulletList().get(i).getView());
                            tower.getBulletList().remove(tower.getBulletList().get(i));

                        }
                        if (!tower.getBulletList().isEmpty() && tower.getBulletList().get(i).isColliding(enemy1)) {
                            gamePane.getChildren().remove(tower.getBulletList().get(i).getView());
                            tower.getBulletList().remove(tower.getBulletList().get(i));
                            enemy1.removeHitPoints(tower.getAttackDamage());
                            if (enemy1.getHitPoints() <= 0) tower.targetEnemy(gameField.getEnemyList()).setDead(true);
                            if (enemy1.isDead()) gameField.updateMoney(tower.targetEnemy(gameField.getEnemyList()));

                        }

                    }


                }
                if (now - timer1 >= MachineGunTower.getAttackCooldown() * 1e9) {

                    gameField.getTowerList().forEach(tower -> {
                        if (tower instanceof MachineGunTower)
                            if (!gameField.getEnemyList().isEmpty()
                                    && tower.targetEnemy(gameField.getEnemyList()).distance(tower) <= tower.getAttackRange()) {

                                tower.createBullet(new Bullet(tower, music.isPlayMusic()));

                            gamePane.getChildren().add(tower.getBullet().getView());
                        }
                    });

                    timer1 = now;
                }
                if (now - timer2 >= NormalTower.getAttackCooldown() * 1e9) {

                    gameField.getTowerList().forEach(tower -> {

                        if (tower instanceof NormalTower) {

                            if (!gameField.getEnemyList().isEmpty() &&
                                    tower.targetEnemy(gameField.getEnemyList()).distance(tower) <= tower.getAttackRange()) {
                                tower.createBullet(new Bullet(tower, music.isPlayMusic()));
                                gamePane.getChildren().add(tower.getBullet().getView());


                            }

                        }
                    });
                    timer2 = now;
                }
                if (now - timer3 >= SniperTower.getAttackCooldown() * 1e9) {

                    gameField.getTowerList().forEach(tower -> {
                        if (tower instanceof SniperTower)
                            if (!gameField.getEnemyList().isEmpty()
                                    && tower.targetEnemy(gameField.getEnemyList()).distance(tower) <= tower.getAttackRange()) {

                                tower.createBullet(new Bullet(tower, music.isPlayMusic()));
                                gamePane.getChildren().add(tower.getBullet().getView());
                            }
                    });

                    timer3 = now;
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
                gameField.gameOver(towerTimer, gamePane);
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
            if (music.isPlayMusic()) music.getMediaButton().play();
            if (play == true) {

                startTimer = new AnimationTimer() {
                    int difficulty = 3;
                    long timer = System.nanoTime();
                    @Override
                    public void handle(long now) {
                        Enemy enemy = null;
                        if (now - timer >= 1 * 1e9) {
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

    public void machineTowerButton() throws IOException {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/machineGunTowerButton.png');";
        MyButton machine = new MyButton("", 64, 64, url);
        machine.setLayoutX(1015);
        machine.setLayoutY(200);
        gamePane.getChildren().add(machine);
        int index = 0;
        createSubScene("MACHINEGUN TOWER", index);
        infoTower[index].setInfo(new MachineGunTower().getInfo());

        machine.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
            System.out.println(music.isPlayMusic());
            gameField.setBuild(true);
            gameScene.setCursor(new ImageCursor(hammer));

            try {

                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, new MachineGunTower()));

            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);

        });
        machine.setOnMouseEntered(action -> {
            machine.setEffect(new DropShadow());
            infoTower[index].moveSubScene();
        });
        machine.setOnMouseExited(action -> {
            infoTower[index].moveSubScene();
        });

    }

    public void normalTowerButton() throws IOException {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/normalTowerButton.png');";
        MyButton normal = new MyButton("", 64, 64, url);
        normal.setLayoutX(1089);
        normal.setLayoutY(200);
        int index = 1;
        createSubScene("  NORMAL TOWER", index);
        infoTower[index].setInfo(new NormalTower().getInfo());
        gamePane.getChildren().add(normal);

        normal.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
            gameField.setBuild(true);
            gameScene.setCursor(new ImageCursor(hammer));

            try {

                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, new NormalTower()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);
        });
        normal.setOnMouseEntered(action -> {
            normal.setEffect(new DropShadow());
            infoTower[index].moveSubScene();
        });
        normal.setOnMouseExited(action -> {
            infoTower[index].moveSubScene();
        });


    }
   /* public void hfhjf(){

        gameField.setBuild(true);
        // gameScene.setCursor(new ImageCursor(hammer));
        infoTower.getBuild().setOnAction(action->{
            if (music.isPlayMusic()) music.getMediaButton().play();
            try {

                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, new NormalTower()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);
        });
    }*/

    public void sniperTowerButton() throws IOException {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/sniperTowerButton.png');";
        MyButton Sniper = new MyButton("", 64, 64, url);
        Sniper.setLayoutX(1163);
        Sniper.setLayoutY(200);
        gamePane.getChildren().add(Sniper);
        int index = 2;
        createSubScene("  SNIPER TOWER", index);
        infoTower[index].setInfo(new SniperTower().getInfo());

        Sniper.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
            gameField.setBuild(true);
            gameScene.setCursor(new ImageCursor(hammer));
            try {
                gameScene.setOnMouseClicked(gameField.buildTower(gamePane, new SniperTower()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameScene.setOnMouseReleased(MouseReleased);

        });
        Sniper.setOnMouseEntered(action -> {
            Sniper.setEffect(new DropShadow());
            infoTower[index].moveSubScene();
        });
        Sniper.setOnMouseExited(action -> {
            infoTower[index].moveSubScene();
        });

    }

    public void createSubScene(String s, int i) throws IOException {

        infoTower[i] = new MySubScene(s);
        gamePane.getChildren().add(infoTower[i]);

    }
}