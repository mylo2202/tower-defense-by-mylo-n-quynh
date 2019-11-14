package game.characters;

import game.GameEntity;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.IOException;

public abstract class Enemy extends GameEntity
{
    private int hitPoints;                      // How Strong enemy is
    private int moveSpeed;                      // How fast enemy moves
    private int reward;                         // Gold reward for enemy's death
    private boolean isDead;                     // Triggering flag for enemy's death and removal
    private boolean reachedGoal;                // Check for enemy reaching the goal alive and removal if it does
    private int level;                          // How difficult to kill the enemy is

    //private GameStage gameStage;
    protected String enemySkin;
    protected Image enemyImage;
    protected ImageView enemyView;

    private Road enemyRoad = new Road();

    public Image getEnemyImage() {
        return enemyImage;
    }

    public void setEnemyImage(Image enemyImage) {
        this.enemyImage = enemyImage;
    }

    public ImageView getEnemyView(){
        return  enemyView;
    }

    public void setEnemyView(ImageView enemyView) {
        this.enemyView = enemyView;
    }

    public Enemy() throws IOException {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEnemySkin() {
        return enemySkin;
    }

    public void setEnemySkin(String enemySkin) {
        this.enemySkin = enemySkin;
    }

    public void takeDamage(int damage)      //reduces enemy's hit points and determines whether it is dead
    {
        hitPoints -= damage;
        if (hitPoints <= 0)
        {
            isDead = true;
            reachedGoal = false;
        }
    }

    public void enemyMove(){

        Path path= new Path();
        //Moving to the starting point
        MoveTo moveTo = new MoveTo(enemyRoad.getSpawner().getX(), enemyRoad.getSpawner().getY());
        path.getElements().add(moveTo);

        for(int i = 0; i < enemyRoad.getRoad().size(); i++)
        {
            LineTo line = new LineTo(enemyRoad.getRoad().get(i).getX(), enemyRoad.getRoad().get(i).getY());
            path.getElements().add(line);
        }

        LineTo endLine = new LineTo(enemyRoad.getGoal().getX(), enemyRoad.getGoal().getY());
        path.getElements().add(endLine);

        //Creating a path transition
        PathTransition pathTransition = new PathTransition();

        //Setting the duration of the path transition
        pathTransition.setDuration(Duration.seconds(10));

        //Setting the node for the transition
        pathTransition.setNode(enemyView);

        //Setting the path
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);

        //Setting auto reverse value to false
        pathTransition.setAutoReverse(false);

        //Playing the animation
        pathTransition.play();
    }
    /*public void remove(){
        enemyImage.
    }*/
}