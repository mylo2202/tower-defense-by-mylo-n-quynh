package game;

import game.characters.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<NormalEnemy> normal= new ArrayList<>();
    private List<Bullet> bullets= new ArrayList<>();
    private List<NormalTower> normalTowers= new ArrayList<>();

    private List<SniperTower> sniperTowers= new ArrayList<>();
    private AnchorPane gamePane;
    private List<MachineGunTower> machineGunTowers= new ArrayList<>();
    private double eventPosX, eventPosY;
    private double translateX, translateY;
   // MachineGunTower machineGunTower = new MachineGunTower();
    private AnimationTimer timer;
    public Controller(){
        gamePane = new AnchorPane();
        creatTower();
    }
    public AnchorPane getGamePane(){
        return gamePane;
    }
    public void setGamePane(AnchorPane anchorPane){
        this.gamePane= anchorPane;
    }
    public  void createGameLoop(){
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

            }
        };
    }
    ImageView machine= new ImageView(new Image("/Image/Tower/machine.png",80,80,false,true));
    public void creatTower() {
        ImageView machine= new ImageView(new Image("/Image/Tower/machine.png",80,80,false,true));
        machine.setLayoutX(1015);
        machine.setLayoutY(200);
        gamePane.getChildren().add(machine);
        buildTower();

    }
    public void buildTower() {

       /* machine.setOnMousePressed(mouseEvent -> {
            MachineGunTower machineGunTower = new MachineGunTower();
            addTower(machineGunTower);
        });*/
        EventHandler<MouseEvent> PressedEventHandler =
                new EventHandler<MouseEvent>() {


                    @Override
                    public void handle(MouseEvent event) {

                        eventPosX = event.getSceneX();
                        eventPosY = event.getSceneY();
                        translateX = ((ImageView) (event.getSource())).getTranslateX();
                        translateY = ((ImageView) (event.getSource())).getTranslateY();
                    }
                };

        EventHandler<MouseEvent> DraggedEventHandler =
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        double offsetX = event.getSceneX() - eventPosX;
                        double offsetY = event.getSceneY() - eventPosY;
                        double newTranslateX = translateX + offsetX;
                        double newTranslateY = translateY + offsetY;

                        ((ImageView) (event.getSource())).setTranslateX(newTranslateX);
                        ((ImageView) (event.getSource())).setTranslateY(newTranslateY);
                        // System.out.println(newTranslateX+"  "+newTranslateY);
                    }
                };
        machineGunTowers.get(machineGunTowers.size()-1).getTowerView().setOnMouseDragged(DraggedEventHandler);
        machineGunTowers.get(machineGunTowers.size()-1).getTowerView().setOnMousePressed(PressedEventHandler);
    }
    public void addTower(MachineGunTower tower){
        machineGunTowers.add(tower);
        tower.getView().setTranslateX(1015);
        tower.getView().setTranslateY(200);

        gamePane.getChildren().addAll(tower.getTowerView());


    }

}
