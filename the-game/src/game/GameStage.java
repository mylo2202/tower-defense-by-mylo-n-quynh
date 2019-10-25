package game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class GameStage {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;

    private int[][] grid ={
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0},
            { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };



    private MyBorderPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    public GameStage(){
        initialiseStage();

    }
    private void initialiseStage(){

        gamePane=new MyBorderPane();


        gameStage=new Stage();

        gameStage.setMaxHeight(HEIGHT);
        gameStage.setMinHeight(HEIGHT);
        gameStage.setMaxWidth(WIDTH);
        gameStage.setMinWidth(WIDTH);
        gameScene=new Scene(gamePane, WIDTH, HEIGHT);
        gameStage.setScene(gameScene);
        tileMap();
        //createBackGround();


    }
    public void createNewGame(Stage menuStage){
        this.menuStage=menuStage;
        this.menuStage.hide();
        gameStage.setTitle("Tower Defense");
        gameStage.show();
    }
//    private void createBackGround(){
//        Image backgroundImage=new Image("/Image/Background/background2.png", WIDTH, HEIGHT,false,true);
//        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
//        gamePane.setBackground(new Background(background));
//
//    }

    //List<ImageView> b= new ArrayList<ImageView>();


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
                //b.add(imageView);

                /*switch (grid[i][j])
                {
                    case 0:
                    {
                        Image img0 =new Image("/Image/PNG/0.png",64,64,false,true);
                        ImageView imageView =new ImageView(img0);
                        b.add(imageView);
                    }
                    case 1:
                    {
                        Image img1 =new Image("/Image/PNG/1.png",64,64,false,true);
                        ImageView imageView =new ImageView(img1);
                        b.add(imageView);
                    }
                    case 2:
                    {
                        Image img2 =new Image("/Image/PNG/2.png",64,64,false,true);
                        ImageView imageView =new ImageView(img2);
                        b.add(imageView);
                    }
                    case 3:
                    {
                        Image img3 =new Image("/Image/PNG/3.png",64,64,false,true);
                        ImageView imageView =new ImageView(img3);
                        b.add(imageView);
                    }
                    case 4:
                    {
                        Image img4 =new Image("/Image/PNG/4.png",64,64,false,true);
                        ImageView imageView =new ImageView(img4);
                        b.add(imageView);
                    }
                    case 5:
                    {
                        Image img5 =new Image("/Image/PNG/5.png",64,64,false,true);
                        ImageView imageView =new ImageView(img5);
                        b.add(imageView);
                    }
                    case 6:
                    {
                        Image img6 =new Image("/Image/PNG/6.png",64,64,false,true);
                        ImageView imageView =new ImageView(img6);
                        b.add(imageView);
                    }
                    case 7:
                    {
                        Image img7 =new Image("/Image/PNG/7.png",64,64,false,true);
                        ImageView imageView =new ImageView(img7);
                        b.add(imageView);
                    }
                    case 8:
                    {
                        Image img8 =new Image("/Image/PNG/8.png",64,64,false,true);
                        ImageView imageView =new ImageView(img8);
                        b.add(imageView);
                    }
                    case 9:
                    {
                        Image img9 =new Image("/Image/PNG/9.png",64,64,false,true);
                        ImageView imageView =new ImageView(img9);
                        b.add(imageView);
                    }
                    case 10:
                    {
                        Image img10 =new Image("/Image/PNG/10.png",64,64,false,true);
                        ImageView imageView =new ImageView(img10);
                        b.add(imageView);
                    }
                    case 20:
                    {
                        Image img20 =new Image("/Image/PNG/20.png",64,64,false,true);
                        ImageView imageView =new ImageView(img20);
                        b.add(imageView);
                    }
                    case 30:
                    {
                        Image img30 =new Image("/Image/PNG/30.png",64,64,false,true);
                        ImageView imageView =new ImageView(img30);
                        b.add(imageView);
                    }
                    case 40:
                    {
                        Image img40 =new Image("/Image/PNG/40.png",64,64,false,true);
                        ImageView imageView =new ImageView(img40);
                        b.add(imageView);
                    }
                    default:
                    {
                        Image img0 =new Image("/Image/PNG/0.png",64,64,false,true);
                        ImageView imageView =new ImageView(img0);
                        b.add(imageView);
                    }
                }*/

                /*for (ImageView imageView : b) {
                    imageView.setLayoutX(j * 64);
                    imageView.setLayoutY(i * 64);
                    gamePane.getChildren().addAll(imageView);
                }*/
            }
        }

        /*Image image =new Image("/Image/PNG/1.png",64,64,false,true);
        ImageView imageView =new ImageView(image);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        gamePane.getChildren().addAll(imageView);*/
    }


}
