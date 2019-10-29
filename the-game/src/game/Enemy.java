package game;

import javafx.animation.PathTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.animation.PathTransition;
import javafx.application.Application;


public class Enemy extends ImageView {
    private String PATH= "/Image/Enemy/towerDefense_tile246.png";
    private ImageView imageView;
    private int PosX;
    private int PosY;

    public int getPosX(){
        return PosX;
    }
    public int getPosY(){
        return PosY;
    }
    public Enemy(String  PATH){
        Image t= new Image(PATH,64,64,false,true);
        imageView =new ImageView(t);

    }
    public ImageView getEnemy(){
        return  imageView;
    }
    public void enemyMove(){
        imageView.setLayoutX(0);
        imageView.setLayoutY(64);
        Path path= new Path();
        //Moving to the starting point
        MoveTo moveTo = new MoveTo(0, 32);

        //Creating 1st line
        LineTo line1 = new LineTo(664, 32);

        //Creating 2nd line
        LineTo line2 = new LineTo(664,224);

        //Creating 3rd line
        LineTo line3 = new LineTo(88,224);

        //Creating 4th line
        LineTo line4 = new LineTo(88, 420);

        //Creating 5th line
        LineTo line5 = new LineTo(664, 420);
        LineTo line6 = new LineTo(664, 608);

        //Creating 5th line
        LineTo line7 = new LineTo(-64, 608);

        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4, line5,line6,line7);

        //Creating a path transition
        PathTransition pathTransition = new PathTransition();

        //Setting the duration of the path transition
        pathTransition.setDuration(Duration.seconds(10));

        //Setting the node for the transition
        pathTransition.setNode(imageView);

        //Setting the path
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);

        //Setting auto reverse value to false
        pathTransition.setAutoReverse(false);

        //Playing the animation
        pathTransition.play();

    }

}

