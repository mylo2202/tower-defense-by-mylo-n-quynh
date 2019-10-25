package game.characters;

import game.characters.*;

public class TankerEnemy extends Enemy {
    public TankerEnemy(int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);

        setHitpoints(600);
        setMovespeed(50);
        setReward(125);
    }
}
