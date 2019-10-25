package game.characters;

import game.characters.*;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);

        setHitpoints(100);
        setMovespeed(200);
        setReward(50);
    }
}
