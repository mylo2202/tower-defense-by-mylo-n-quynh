package game.characters;

import game.characters.*;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class NormalEnemy extends Enemy
{
    private  String URL="/Image/Enemy/normal.png";
    private ImageView skin;
   // private final int speed = 1;
    public NormalEnemy(int posX, int posY, int width, int height,int speed)
    {
        //there is serious problem with constructors here
        //i'll leave it for the time being
        //i mean, seriously, what the hell is this for??
        //it is supposed that a super() method be written here

        super(posX, posY, width, height);

        setHitPoints(200);
        setMoveSpeed(100);
        setReward(50);
        Image image =new Image(URL,64,64,false,true);
        skin = new ImageView(image);

    }

    public ImageView getEnemy(){
        return skin;
    }
    public void loadEnemy(){
        skin.setLayoutX(getPosX());
        skin.setLayoutY(getPosY());
    }
    public void enemyMove(){
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
        pathTransition.setNode(skin);

        //Setting the path
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);

        //Setting auto reverse value to false
        pathTransition.setAutoReverse(false);

        //Playing the animation
        pathTransition.play();
    }

}