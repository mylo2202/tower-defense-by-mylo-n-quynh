package game.drawers;

import game.Controller;
import game.characters.Enemy;
import game.characters.MachineGunTower;
import game.characters.NormalEnemy;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
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
    ImageView machine = new ImageView(new Image("/Image/Tower/machineGunTower.png", 80, 80, false, true));
    private AnimationTimer gameTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private MyLabel Money;
    private ImageView[] lifes;
    private int life = 4;
    private TileMap map = new TileMap();
    private Controller controller;
    private List<MachineGunTower> machineGunTowers = new ArrayList<>();
    private double eventPosX, eventPosY;
    private double translateX, translateY;

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

    public void createNewGame(Stage menuStage) throws IOException {
        this.menuStage = menuStage;
        this.menuStage.hide();

        map = new TileMap();
        drawPanel();
        createPanelControl();
        map.drawMap(gamePane);
        createButton();
        creatTower();

        gameStage.setTitle("Tower Defense");
        gameStage.show();
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

    public void createButton() {
        buttonStart();
    }

    public void buttonStart() {
        String url = "-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start = new MyButton("START", 45, 190, url);
        Start.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
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
            machineGunTowers.forEach(machineGunTower -> {
                machineGunTower.setPos(new Point2D(machineGunTower.getTowerView().getTranslateX(), machineGunTower.getTowerView().getTranslateY()));
            });

            gameTimer = new AnimationTimer() {
                long timer = System.nanoTime();

                @Override
                public void handle(long now) {

                        /*System.out.println((finalE.getEnemyView().getTranslateX() + 32) + " " +
                                (finalE.getEnemyView().getTranslateY() + 32));*/
//System.out.println(x+"  "+y);

                    double posEX = finalE.getEnemyView().getTranslateX();
                    double posEY = finalE.getEnemyView().getTranslateY();
                    //  System.out.println(-Math.toDegrees(Math.atan(x - xx / y - yy)));
                    machineGunTowers.forEach(machineGunTower -> {
                        if (posEY < machineGunTower.getPos().getY()) {

                            machineGunTower.getTowerView().setRotate(-Math.toDegrees(Math.atan((posEX - machineGunTower.getPos().getX()) / (posEY - machineGunTower.getPos().getY()))));
                        } else
                            machineGunTower.getTowerView().setRotate(180 - Math.toDegrees(Math.atan((posEX - machineGunTower.getPos().getX()) / (posEY - machineGunTower.getPos().getY()))));
                    });

                    timer = now;

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
        Money = new MyLabel("MONEY : 0050");
        Money.setLayoutX(map.getGrid()[0].length * map.getSize() + (map.getSCREEN_WIDTH() - map.getGrid()[0].length * map.getSize()) / 2 - 95);
        Money.setLayoutY(64);
        gamePane.getChildren().add(Money);
    }

    public void removeLife() {
        gamePane.getChildren().remove(lifes[life]);
        life--;
        if (life == 0) {
            gameTimer.stop();
        }
    }

    public void creatTower() {

        machine.setLayoutX(1015);
        machine.setLayoutY(200);
        gamePane.getChildren().add(machine);
        machine.setOnMousePressed(mouseEvent -> {
            MachineGunTower machineGunTower = null;
            try {
                machineGunTower = new MachineGunTower();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /*machineGunTower.getView().setTranslateX(1015);
            machineGunTower.getView().setTranslateY(200);*/
            machineGunTowers.add(machineGunTower);
            gamePane.getChildren().addAll(machineGunTower.getTowerView());
            buildTower();
        });


    }

    public void buildTower() {
        EventHandler<MouseEvent> PressedEventHandler;
        EventHandler<MouseEvent> DraggedEventHandler;
        EventHandler<MouseEvent> ExitEventHandler;

        final boolean[] t = new boolean[1];
        t[0] = false;
        PressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                eventPosX = event.getSceneX();
                eventPosY = event.getSceneY();
                translateX = ((ImageView) (event.getSource())).getTranslateX();
                translateY = ((ImageView) (event.getSource())).getTranslateY();
            }
        };

        DraggedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (t[0] == false) {
                    double offsetX = event.getSceneX() - eventPosX;
                    double offsetY = event.getSceneY() - eventPosY;
                    double newTranslateX = translateX + offsetX;
                    double newTranslateY = translateY + offsetY;

                    ((ImageView) (event.getSource())).setTranslateX(newTranslateX);
                    ((ImageView) (event.getSource())).setTranslateY(newTranslateY);
                }
                // System.out.println(newTranslateX+"  "+newTranslateY);
            }
        };

        ExitEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                t[0] = true;

                translateX = event.getSceneX();
                translateY = event.getSceneY();

            }
        };
        machineGunTowers.get(machineGunTowers.size() - 1).getTowerView().setOnMousePressed(PressedEventHandler);
        machineGunTowers.get(machineGunTowers.size() - 1).getTowerView().setOnMouseDragged(DraggedEventHandler);
        machineGunTowers.get(machineGunTowers.size() - 1).getTowerView().setOnMouseReleased(ExitEventHandler);

    }
}