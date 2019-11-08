package game.characters;

import game.GameStage;
import game.GameStage.*;
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

public abstract class Enemy extends ImageView
{
    private int hitPoints;                      // How Strong enemy is
    private int moveSpeed;                      // How fast enemy moves
    private int reward;                         // Gold reward for enemy's death
    private boolean isDead;                     // Triggering flag for enemy's death and removal
    private boolean reachedGoal;                // Check for enemy reaching the goal alive and removal if it does

    private GameStage gameStage;
    private String enemySkin;
    private ImageView imageView;
    private int posX;
    private int posY;

    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    public ImageView getEnemyView(){
        return  imageView;
    }

    public Enemy(String enemySkin) //int hitPoints, int moveSpeed, int reward)
    {
        Image t= new Image(enemySkin,64,64,false,true);
        imageView =new ImageView(t);
//        this.hitPoints = hitPoints;
//        this.moveSpeed = moveSpeed;
//        this.reward = reward;
        isDead = false;
        reachedGoal = false;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    public int getMoveSpeed()
    {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }

    public int getReward()
    {
        return reward;
    }

    public void setReward(int reward)
    {
        this.reward = reward;
    }

    public boolean isDead()
    {
        return isDead;
    }

    public boolean reachedGoal()
    {
        return reachedGoal;
    }

    //enemy move method goes here

    public void takeDamage(int damage)      //reduces enemy's hit points and determines whether it is dead
    {
        hitPoints -= damage;
        if (hitPoints <= 0)
        {
            isDead = true;
            reachedGoal = false;
        }
    }
//the following method is still crap and should be replaced by one that actually let enemies find their paths
    public void enemyMove(){
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        Path path= new Path();
        //Moving to the starting point
        MoveTo moveTo = new MoveTo(0, 1.5*gameStage.GRID_SIZE);
        path.getElements().add(moveTo);

        /*//Creating 1st line
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
        LineTo line7 = new LineTo(-64, 608);*/


        //path.getElements().addAll(line1, line2, line3, line4, line5,line6,line7);

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