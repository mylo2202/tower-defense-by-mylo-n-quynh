package game.characters;

import game.characters.*;

//should this class be abstract?

public abstract class GameEntity
{
    //methods for getting coordinates and sizes go here
    private int posX;
    private int posY;

    public GameEntity() {}

    GameEntity(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public final int getPosX()
    {
        return posX;
    }

    public final void setPosX(int posX)
    {
        this.posX = posX;
    }

    public final int getPosY()
    {
        return posY;
    }

    public final void setPosY(int posY)
    {
        this.posY = posY;
    }


    //movement methods maybe go here
}