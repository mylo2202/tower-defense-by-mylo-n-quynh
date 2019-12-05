package game.drawers;

import game.GameField;
import game.Music;
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

public class GameStage {

    private AnimationTimer startTimer;
    private AnimationTimer gameTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;


    private Image hammer;
    private ImageView life;

    private TileMap map = new TileMap();
    private MySubScene[] infoTower;

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

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
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

    public void setMusic(Music music) {
        this.music = music;
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
        life.setLayoutY(134);

        gameField.getLife().setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 40);
        gameField.getLife().setLayoutY(128);

        gameField.getLabelLevel().setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        gameField.getLabelLevel().setLayoutY(580);

        gameField.getMoneyLabel().setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        gameField.getMoneyLabel().setLayoutY(64);
        gamePane.getChildren().addAll(gameField.getLife(), gameField.getLabelLevel(), gameField.getMoneyLabel(), life);
    }

    public void createButton() throws IOException {
        buttonStart();
        buttonExit();
        machineTowerButton();
        normalTowerButton();
        sniperTowerButton();
    }


    public void createNewGame(Stage menuStage) throws IOException {
        this.menuStage = menuStage;
        this.menuStage.hide();

        infoTower = new MySubScene[3];
        drawPanel();
        hammer = new Image("/Image/Tower/Hammer.png");
        map.drawMap(gamePane);
        createButton();
        gameLoop();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }

    public void gameLoop() {
        gameField.getTowerList().forEach(tower -> tower.setPos(new Point2D(tower.getView().getTranslateX(), tower.getView().getTranslateY()))
        );

        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameField.upgradeTower();
                gameField.sellTower(gamePane);
                //System.out.println(gameField.getLevel());
                for (Tower tower : gameField.getTowerList()) {
                    tower.update(tower.targetEnemy(gameField.getEnemyList()));

                    for (int i = 0; i < tower.getBulletList().size(); i++) {

                        tower.getBulletIndex(i).update(tower.targetEnemy(gameField.getEnemyList()));

                        //   Enemy enemy = tower.targetEnemy(gameField.getEnemyList());

                        double range = Math.sqrt(Math.pow(tower.getBulletIndex(i).getView().getTranslateX() - tower.getBulletIndex(i).getPos().getX(), 2)
                                + Math.pow(tower.getBulletIndex(i).getView().getTranslateY() - tower.getBulletIndex(i).getPos().getY(), 2));
                        if (range >= tower.getAttackRange() || gameField.getEnemyList().isEmpty()) {
                            //   System.out.println(bullet.getView().getTranslateX()+"     "+bullet.getView().getTranslateY());

                            gamePane.getChildren().remove(tower.getBulletIndex(i).getView());
                            tower.getBulletList().remove(tower.getBulletIndex(i));
                            break;
                        }
                        if (!tower.getBulletList().isEmpty() && tower.getBulletIndex(i).isColliding(tower.targetEnemy(gameField.getEnemyList()))) {
                            gamePane.getChildren().remove(tower.getBulletIndex(i).getView());
                            tower.getBulletList().remove(tower.getBulletIndex(i));
                            //System.out.println(tower.getBulletList().size());
                            tower.targetEnemy(gameField.getEnemyList()).removeHitPoints(tower.getAttackDamage());
                            if (tower.targetEnemy(gameField.getEnemyList()).getHitPoints() <= 0)
                                tower.targetEnemy(gameField.getEnemyList()).setDead(true);
                            if (tower.targetEnemy(gameField.getEnemyList()).isDead())
                                gameField.updateMoney(tower.targetEnemy(gameField.getEnemyList()));
                        }
                    }
                    tower.towerAttack(now, gameField, gamePane);
                }

                gameField.getTowerList().forEach(tower -> {
                    if(play) tower.getLabelLevel().setText("level " + tower.getTowerLevel());
                    else tower.getLabelLevel().setText("");
                });

                if (!gameField.getEnemyList().isEmpty()) {
                    //  System.out.println(gameField.getEnemyList().get(0).getView().getTranslateX() + " " + gameField.getEnemyList().get(0).getView().getTranslateY());

                    for (int i = 0; i < gameField.getEnemyList().size(); i++) {
                        if (gameField.checkRemoveEnemy(i)) {
                            gamePane.getChildren().remove(gameField.getEnemyList().get(i).getView());
                            gameField.removeEnemy(i);
                        }
                    }
                }

                if (gameField.getEnemyList().isEmpty() && gameField.getLevel() > 0 && !play && gameField.hasGeneratedEnemy()) {
                    gameField.setMoney(gameField.getMoney() + 200);
                    gameField.updateMoney();
                    gameField.setGeneratedEnemy(false);
                    play = true;
                }

                if(gameField.getLives() < 0) play = false;
                gameField.gameOver(gameTimer, startTimer,gameStage,menuStage);
            }
        };

        gameTimer.start();
    }
    public void buttonStart() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton start = new MyButton("START", 45, 190, url);
        start.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        start.setLayoutY(640);

        start.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();


            if (play) {
                gameField.setLevel(gameField.getLevel() + 1);
                gameField.updateLabelLevel();
                play = false;
                //System.out.println("--------------- LEVEL " + gameField.getLevel() + " ---------------");
                //System.out.println("diff factor = " + Math.pow(2, gameField.getLevel() - 1));
                startTimer = new AnimationTimer() {
                    long timer = System.nanoTime();
                    int difficulty = (int) (10 * Math.pow(2, gameField.getLevel() - 1));
                    @Override
                    public void handle(long now) {
                        if (now - timer >= 0.25e9 && difficulty > 0) {
                            //System.out.println("looking good");
                            try {
                                gameField.generateEnemy(gameField.getEnemyList(), difficulty);
                                //System.out.println("size = " + gameField.getEnemyList().size());
                                gameField.getEnemyList().get(gameField.getEnemyList().size() - 1).enemyMove().play();
                                gamePane.getChildren().add(gameField.getEnemyList().get(gameField.getEnemyList().size() - 1).getView());
                                difficulty -= gameField.getEnemyList().get(gameField.getEnemyList().size() - 1).getLevel();
                                //System.out.println("difficulty = " + difficulty);
                                if (difficulty <= 0) {
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

        gamePane.getChildren().add(start);
    }

    public void buttonExit() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/towerDefense_tile017.png');";
        MyButton exit = new MyButton("", 64, 64, url);
        exit.setLayoutX(map.getSCREEN_WIDTH() - 69);
        exit.setLayoutY(4);
        exit.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
            gameStage.close();
            //  menuStage.show();
        });
        gamePane.getChildren().add(exit);
    }
    public void machineTowerButton() throws IOException {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/machineGunTowerButton.png');";
        MyButton machine = new MyButton("", 64, 64, url);
        machine.setLayoutX(map.getMAP_WIDTH() * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 64 * 1.5);
        machine.setLayoutY(200);
        gamePane.getChildren().add(machine);
        int index = 0;
        createSubScene("MACHINE GUN TOWER", index);
        infoTower[index].setInfo(new MachineGunTower().getInfo());

        machine.setOnAction(actionEvent -> {
            if (music.isPlayMusic()) music.getMediaButton().play();
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
            machine.setEffect(null);
            infoTower[index].moveSubScene();
        });

    }

    public void normalTowerButton() throws IOException {

        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/normalTowerButton.png');";
        MyButton normal = new MyButton("", 64, 64, url);
        normal.setLayoutX(map.getMAP_WIDTH() * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 64 * 0.5);
        normal.setLayoutY(200);
        int index = 1;
        createSubScene("NORMAL TOWER", index);
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
            normal.setEffect(null);
            infoTower[index].moveSubScene();
        });


    }

    public void sniperTowerButton() throws IOException {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/Tower/sniperTowerButton.png');";
        MyButton sniper = new MyButton("", 64, 64, url);
        sniper.setLayoutX(map.getMAP_WIDTH() * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 + 64 * 0.5);
        sniper.setLayoutY(200);
        gamePane.getChildren().add(sniper);
        int index = 2;
        createSubScene("SNIPER TOWER", index);
        infoTower[index].setInfo(new SniperTower().getInfo());

        sniper.setOnAction(actionEvent -> {
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
        sniper.setOnMouseEntered(action -> {
            sniper.setEffect(new DropShadow());
            infoTower[index].moveSubScene();
        });
        sniper.setOnMouseExited(action -> {
            sniper.setEffect(null);
            infoTower[index].moveSubScene();
        });

    }

    public void createSubScene(String s, int i) throws IOException {

        infoTower[i] = new MySubScene(s);
        gamePane.getChildren().add(infoTower[i]);

    }
}