package game;

import game.characters.Enemy;
import game.characters.NormalEnemy;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class GameStage {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private  AnimationTimer gameTimer;

    private int[][] grid ={
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5,0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5,0 },
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,0 },
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 },
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5,0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5,0 },
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 }
    };

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;



    private MyLabel pointLabel;
    private MyLabel Money;
    private int point;
    private ImageView [] lifes;
    private int life=4;

    private boolean play;//=false;
    private GameField draft;

    public GameStage(){
        initialiseStage();
       // createMouseListeners();
    }

    /*private void createMouseListeners() {
        gameScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
        gameScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
    }*/

    private void initialiseStage(){
        gamePane=new AnchorPane();
        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage=new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);

    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();

        tileMap();
        createPanelControl();

        play=true;
        createGameLoop();
        createButton();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }
    public Stage getStage(){
        return gameStage;
    }

    public void tileMap()
    {
        for(int i = 0; i < 12; i++)
        {
            for(int j = 0; j < 12; j++)
            {
                Image image =new Image("/Image/PNG/" + grid[i][j] +  ".png",64,64,false,true);
                ImageView imageView =new ImageView(image);
                imageView.setLayoutX(j*64);
                imageView.setLayoutY(i*64);
                gamePane.getChildren().addAll(imageView);
            }
        }
        ImageView panel = new ImageView("/Image/UI/green_panel.png");
        panel.setLayoutX(768);
        panel.setLayoutY(0);
        gamePane.getChildren().add(panel);

    }

    private void createGameLoop(){
        {
            /*Enemy e = new Enemy();
            e.getEnemy().setLayoutX(0);
            e.getEnemy().setLayoutY(64);*/
            NormalEnemy e = new NormalEnemy(0,64,64,64,5);
            e.loadEnemy();
            gamePane.getChildren().add(e.getEnemy());
            if(play==true){
                gameTimer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                       e.enemyMove();
                    }


                };
                gameTimer.start();
            }


        }


    }
    public void createButton(){
        buttonStart();

    }

    public void buttonStart(){
        String url="-fx-background-color: transparent; -fx-background-image: url('/Image/UI/green_button13.png');";
        MyButton Start= new MyButton("START",45,190,url);
        Start.setLayoutX(800);
        Start.setLayoutY(640);
        Start.setOnAction(actionEvent -> {
            NormalEnemy e = new NormalEnemy(0,64,64,64,5);
            e.loadEnemy();
            e.enemyMove();
            gamePane.getChildren().add(e.getEnemy());
        });
        gamePane.getChildren().add(Start);

    }

    private void createPanelControl(){
        pointLabel=new MyLabel("POINT : 00");
        pointLabel.setLayoutX(768 +32);
        pointLabel.setLayoutY(64-32);
        gamePane.getChildren().add(pointLabel);
        lifes= new ImageView[4];
        for (int i=0;i<4;++i){
            lifes[i]= new ImageView("/Image/UI/heart1.png");
            lifes[i].setLayoutX(768+43+(i*45));
            lifes[i].setLayoutY(64+45+8-32);
            gamePane.getChildren().add(lifes[i]);
        }
        Money =new MyLabel("MONEY : 0050");
        Money.setLayoutX(768+32);
        Money.setLayoutY(64+45+24);
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
