package game.drawers;

import game.GameField;
import game.Music;
import game.characters.Enemy;
import game.characters.Tower;
import game.characters.Towers.MachineGunTower;
import game.characters.Towers.NormalTower;
import game.characters.Towers.SniperTower;
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
import java.util.ArrayList;
import java.util.List;

public class GameStage {

    private AnimationTimer startTimer;
    private AnimationTimer gameTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;


    private Image hammer = new Image("/Image/Tower/Hammer.png");
    private ImageView life;

    private TileMap map = new TileMap();
    private List<MySubScene> infoTower = new ArrayList<>();

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

        gameField.getLife().setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 40);
        gameField.getLife().setLayoutY(128);
        gamePane.getChildren().add(gameField.getLife());

        gameField.getMoney().setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        gameField.getMoney().setLayoutY(64);
        gamePane.getChildren().add(gameField.getMoney());

        gameField.getLabelLevel().setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        gameField.getLabelLevel().setLayoutY(580);
        gamePane.getChildren().add(gameField.getLabelLevel());
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
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Tower tower : gameField.getTowerList()) {
                    tower.update(tower.targetEnemy(gameField.getEnemyList()));

                    for (int i = 0; i < tower.getBulletList().size(); i++) {

                        tower.getBulletIndex(i).update(tower.targetEnemy(gameField.getEnemyList()));

                        Enemy enemy1 = tower.targetEnemy(gameField.getEnemyList());

                        double distance = Math.sqrt(Math.pow(tower.getBulletIndex(i).getView().getTranslateX() - tower.getBulletIndex(i).getPos().getX(), 2)
                                + Math.pow(tower.getBulletIndex(i).getView().getTranslateY() - tower.getBulletIndex(i).getPos().getY(), 2));
                        if (distance >= tower.getAttackRange() || gameField.getEnemyList().isEmpty()) {
                            //   System.out.println(bullet.getView().getTranslateX()+"     "+bullet.getView().getTranslateY());

                            gamePane.getChildren().remove(tower.getBulletIndex(i).getView());
                            tower.getBulletList().remove(tower.getBulletIndex(i));
                            break;
                        }
                        if (!tower.getBulletList().isEmpty() && tower.getBulletIndex(i).isColliding(enemy1)) {
                            gamePane.getChildren().remove(tower.getBulletIndex(i).getView());
                            tower.getBulletList().remove(tower.getBulletIndex(i));
                            System.out.println(tower.getBulletList().size());
                            enemy1.removeHitPoints(tower.getAttackDamage());
                            if (enemy1.getHitPoints() <= 0) tower.targetEnemy(gameField.getEnemyList()).setDead(true);
                            if (enemy1.isDead()) gameField.updateMoney(tower.targetEnemy(gameField.getEnemyList()));
                        }
                    }
                    tower.towerAttack(now, gameField, gamePane);
                }

                if (!gameField.getEnemyList().isEmpty()) {
                    //  System.out.println(gameField.getEnemyList().get(0).getView().getTranslateX() + " " + gameField.getEnemyList().get(0).getView().getTranslateY());

                    for (int i = 0; i < gameField.getEnemyList().size(); i++) {
                        if (gameField.checkRemoveEnemy(i)) {
                            gamePane.getChildren().remove(gameField.getEnemyList().get(i).getView());
                            gameField.removeEnemy(i);
                        }
                    }
                }

                if (gameField.getEnemyList().isEmpty() && gameField.getLevel() > 0 && !play && gameField.hasGeneratedEnemy())
                {
                    gameField.setMoney(gameField.getMoney() + 200 + gameField.getLevel()*25);
                    gameField.updateMoney();
                    gameField.setGeneratedEnemy(false);
                    play = true;
                }

                if(gameField.getLives() < 0) play = false;
                gameField.gameOver(gameTimer, gamePane);
            }
        };

        gameTimer.start();
    }
    public void buttonStart() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start = new MyButton("START", 45, 190, url);
        Start.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        Start.setLayoutY(640);

        Start.setOnAction(actionEvent -> {
            gameField.updateLevel();
            if (music.isPlayMusic()) music.getMediaButton().play();
            gameField.setLevel(gameField.getLevel() + 1);
            if (play)
            {
                play = false;
                System.out.println("--------------- LEVEL " + gameField.getLevel() + " ---------------");
                startTimer = new AnimationTimer() {
                    long timer = System.nanoTime();
                    int difficulty = (int) (10 * Math.pow(2, gameField.getLevel() - 1));
                    @Override
                    public void handle(long now) {
                        if (now - timer >= 1e9 && difficulty > 0) {
                            //System.out.println("looking good");
                            try {
                                gameField.generateEnemy(gameField.getEnemyList(), difficulty);
                                System.out.println("size = " + gameField.getEnemyList().size());
                                gameField.getEnemyList().get(gameField.getEnemyList().size() - 1).enemyMove().play();
                                gamePane.getChildren().add(gameField.getEnemyList().get(gameField.getEnemyList().size() - 1).getView());
                                difficulty -= gameField.getEnemyList().get(gameField.getEnemyList().size() - 1).getLevel();
                                System.out.println("difficulty = " + difficulty);
                                if(difficulty <= 0)
                                {
                                    startTimer.stop();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            timer = now;
                        }
                    }
                };
                startTimer.start();
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
        createSubScene("MACHINEGUN TOWER");
        int index = infoTower.size() - 1;
        infoTower.get(index).setInfo(new MachineGunTower().getInfo());

        machine.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
            //System.out.println(music.isPlayMusic());
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
            infoTower.get(index).moveSubScene();
        });
        machine.setOnMouseExited(action -> {
            infoTower.get(index).moveSubScene();
            machine.setEffect(null);
        });

    }

    public void normalTowerButton() throws IOException {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/normalTowerButton.png');";
        MyButton normal = new MyButton("", 64, 64, url);
        normal.setLayoutX(1089);
        normal.setLayoutY(200);

        createSubScene("NORMAL TOWER");
        int index = infoTower.size() - 1;
        infoTower.get(index).setInfo(new NormalTower().getInfo());
        gamePane.getChildren().add(normal);

        normal.setOnAction(actionEvent -> {
            gameField.updateLabelLevel();
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
            infoTower.get(index).moveSubScene();
        });
        normal.setOnMouseExited(action -> {
            normal.setEffect(null);
            infoTower.get(index).moveSubScene();
        });


    }

    public void sniperTowerButton() throws IOException {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/sniperTowerButton.png');";
        MyButton Sniper = new MyButton("", 64, 64, url);
        Sniper.setLayoutX(1163);
        Sniper.setLayoutY(200);
        gamePane.getChildren().add(Sniper);

        createSubScene("SNIPER TOWER");
        int index = infoTower.size() - 1;
        infoTower.get(index).setInfo(new SniperTower().getInfo());

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
            infoTower.get(index).moveSubScene();
        });
        Sniper.setOnMouseExited(action -> {
            Sniper.setEffect(null);
            infoTower.get(index).moveSubScene();
        });

    }

    public void createSubScene(String s) throws IOException {
        MySubScene mySubScene = new MySubScene(s);
        infoTower.add(mySubScene);
        gamePane.getChildren().add(infoTower.get(infoTower.size() - 1));

    }
}