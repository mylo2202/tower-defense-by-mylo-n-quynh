package game.characters;

import game.characters.*;

public class BossEnemy extends Enemy {
    public BossEnemy(int posX, int posY, int width, int height)
    {
        super(posX, posY, width, height);

        setHitpoints(1600);
        setMovespeed(25);
        setReward(350);
    }
}
