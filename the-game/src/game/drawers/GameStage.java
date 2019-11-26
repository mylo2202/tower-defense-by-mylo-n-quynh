package game.drawers;

import game.GameField;
import game.characters.Bullet;
import game.characters.Enemy;
import game.characters.MachineGunTower;
import game.characters.SmallerEnemy;
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
import java.util.ArrayList;
import java.util.List;

public class GameStage {

    private AnimationTimer gameTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private MyLabel Money;
    EventHandler<MouseEvent> MouseReleased = new EventHandler<>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            gameScene.setCursor(Cursor.DEFAULT);
        }

    };
    private ImageView[] lifes;
    private int life = 4;
    private TileMap map = new TileMap();

    private List<MachineGunTower> machineGunTowers = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private double eventPosX, eventPosY;
    int money = 50;


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

    public void drawPanel() {
        Image panel = new Image("/Image/UI/green_panel.png",
                map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize(), map.getSCREEN_HEIGHT(), false, true);
        ImageView panelView = new ImageView(panel);
        panelView.setLayoutX(map.getGrid()[0].length * map.getSize());
        panelView.setLayoutY(0);
        gamePane.getChildren().add(panelView);
    }

    private boolean build = false;

    public void createNewGame(Stage menuStage) throws IOException {
        this.menuStage = menuStage;
        this.menuStage.hide();

        map = new TileMap();
        drawPanel();
        createPanelControl();
        map.drawMap(gamePane);
        createButton();

        gameStage.setTitle("Tower Defense");
        gameStage.show();
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

    private Image hammer = new Image("/Image/Tower/Hammer.png");

    public void buttonStart() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start = new MyButton("START", 45, 190, url);
        Start.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {

            machineGunTowers.forEach(machineGunTower -> machineGunTower.setPos(new Point2D(machineGunTower.getView().getTranslateX(),
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


                    machineGunTowers.forEach(machineGunTower -> {
                        machineGunTower.update(gameField.getEnemyList().get(0));
                        gameField.getBulletList().forEach(Bullet::update);
                    });
                    if (now - timer1 >= 0.25 * 1e9) {
                        bullet = new Bullet(machineGunTowers.get(0));
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

    public void createPanelControl() {
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
    public EventHandler buildMachineTower() throws IOException {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            MachineGunTower machineGunTower = new MachineGunTower();

            @Override
            public void handle(MouseEvent mouseEvent) {
                if (build == true && money >= machineGunTower.getBuildCost()) {
                    eventPosX = mouseEvent.getSceneX();
                    eventPosY = mouseEvent.getSceneY();

                    int i = (int) eventPosY / map.getGRID_SIZE();
                    int j = (int) eventPosX / map.getGRID_SIZE();

                    if (i < 12 && j < 12 && map.getGrid()[i][j] == 0) {
                        machineGunTowers.add(machineGunTower);
                        machineGunTower.getView().setTranslateX(j * map.getGRID_SIZE());
                        machineGunTower.getView().setTranslateY(i * map.getGRID_SIZE());
                        gamePane.getChildren().add(machineGunTower.getView());
                        money = money - machineGunTower.getBuildCost();
                        String setTextMoney = "MONEY : ";
                        if (money < 10) setTextMoney = setTextMoney + "0";
                        Money.setText(setTextMoney + money);
                    }
                }
                build = false;
            }

        };
        return eventHandler;
    }

    public EventHandler buildSniperTower() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        };
        return eventHandler;
    }

    public EventHandler buildNormalTower() {
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
            build = true;
            gameScene.setCursor(new ImageCursor(hammer));
            try {
                gameScene.setOnMouseClicked(buildMachineTower());
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
            build = true;
            gameScene.setCursor(new ImageCursor(hammer));
            gameScene.setOnMouseClicked(buildNormalTower());
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
            build = true;
            gameScene.setCursor(new ImageCursor(hammer));
            gameScene.setOnMouseClicked(buildSniperTower());
            gameScene.setOnMouseReleased(MouseReleased);

        });

    }
}