package game.characters;

import game.characters.*;
import javafx.animation.PathTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public abstract class Enemy extends GameEntity
{
    private int hitPoints;                      // How Strong enemy is
    private int moveSpeed;                      // How fast enemy moves
    private int reward;                         // Gold reward for enemy's death
    private boolean isDead;                     // Triggering flag for enemy's death and removal
    private boolean reachedGoal;                // Check for enemy reaching the goal alive and removal if it does

    public Enemy(int posX, int posY, int width, int height) //int hitPoints, , int reward)
    {
        super(posX, posY, width, height);

//        this.hitPoints = hitPoints;
        this.moveSpeed = moveSpeed;
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

    public void enemyMove(){


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
}