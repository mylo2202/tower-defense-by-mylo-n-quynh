package game.characters;

import game.characters.*;

import javax.swing.text.html.ImageView;

//should this class be abstract?

public abstract class GameEntity
{
    //methods for getting coordinates and sizes go here
    private int posX;
    private int posY;
    private int width;
    private int height;
   // private ImageView;

    public GameEntity() {};

    GameEntity(int posX, int posY, int width, int height)
    {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
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

    public final int getWidth()
    {
        return width;
    }

    public final void setWidth(int width)
    {
        this.width = width;
    }

    public final int getHeight()
    {
        return height;
    }

    public final void setHeight(int height)
    {
        this.height = height;
    }


}