package game.characters;

import game.characters.*;

public class NormalEnemy extends Enemy
{
    public NormalEnemy(int posX, int posY, int width, int height)
    {
        //there is serious problem with constructors here
        //i'll leave it for the time being
        //i mean, seriously, what the hell is this for??
        //it is supposed that a super() method be written here

        super(posX, posY, width, height);

        setHitpoints(200);
        setMovespeed(100);
        setReward(50);

    }


}