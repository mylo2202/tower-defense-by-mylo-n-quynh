package game.characters;

import game.characters.Entity;

public class Enemy extends Entity
{
    private int hitPoints;                      // How Strong enemy is
    private int moveSpeed;                      // How fast enemy moves
    private int reward;                         // Gold reward for enemy's death
    private boolean isDead;                     // Triggering flag for enemy's death and removal
    private boolean reachedGoal;                // Check for enemy reaching the goal alive and removal if it does

    public Enemy(int posX, int posY, int width, int height) //int hitPoints, int moveSpeed, int reward)
    {
        super(posX, posY, width, height);

//        this.hitPoints = hitPoints;
//        this.moveSpeed = moveSpeed;
//        this.reward = reward;
        isDead = false;
        reachedGoal = false;
    }

    public int getHitpoints()
    {
        return hitPoints;
    }

    public void setHitpoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    public int getMovespeed()
    {
        return moveSpeed;
    }

    public void setMovespeed(int moveSpeed)
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
}